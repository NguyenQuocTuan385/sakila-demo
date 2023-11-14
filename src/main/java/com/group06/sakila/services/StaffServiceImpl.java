package com.group06.sakila.services;

import com.group06.sakila.component.JwtTokenUtil;
import com.group06.sakila.component.RefreshTokenUtil;
import com.group06.sakila.entities.Staff;
import com.group06.sakila.exceptions.NotFoundException;
import com.group06.sakila.repositories.StaffRepository;
import com.group06.sakila.request_models.StaffRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final PasswordEncoder passwordEncoder;
    private final StaffRepository staffRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenUtil refreshTokenUtil;
    @Override
    public Staff createStaff(StaffRegisterRequest staffRequest) {
        String username = staffRequest.getUsername();
        if (staffRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        Staff newStaff = Staff.builder()
                .firstName(staffRequest.getFirstName())
                .lastName(staffRequest.getLastName())
                .addressId(staffRequest.getAddressId())
                .email(staffRequest.getEmail())
                .storeId(staffRequest.getStoreId())
                .username(staffRequest.getUsername())
                .picture(staffRequest.getPicture())
                .password(staffRequest.getPassword())
                .active(true)
                .lastUpdate(LocalDateTime.now())
                .build();
        String password = staffRequest.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        newStaff.setPassword(encodePassword);
        return staffRepository.save(newStaff);
    }

//    @Override
//    public String login(String username, String password) throws Exception {
//        Optional<Staff> optionalStaff = staffRepository.findByUsername(username);
//        if (optionalStaff.isEmpty())
//            throw new NotFoundException("Invalid username / password");
//        Staff existingStaff = optionalStaff.get();
//        if (!passwordEncoder.matches(password, existingStaff.getPassword())) {
//            throw new BadCredentialsException("Wrong password");
//        }
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                username, password,
//                existingStaff.getAuthorities()
//        );
//        authenticationManager.authenticate(authenticationToken);
//        return jwtTokenUtil.generateToken(existingStaff);
//    }

    @Override
    public HashMap<String, String> login(String username, String password) throws Exception {
        Optional<Staff> optionalStaff = staffRepository.findByUsername(username);
        String newJwtToken;

        if (optionalStaff.isEmpty())
            throw new NotFoundException("Invalid username / password");

        Staff existingStaff = optionalStaff.get();

        if (!passwordEncoder.matches(password, existingStaff.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password,
                existingStaff.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        newJwtToken = jwtTokenUtil.generateToken(existingStaff);

        String rfToken = refreshTokenUtil.generateRefreshToken(newJwtToken, existingStaff);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", newJwtToken);
        map.put("refresh_token", rfToken);

        return map;
    }

    @Override
    public HashMap<String, String> refresh(String rfToken) throws Exception {
        Staff staff = staffRepository.findByRefreshToken(rfToken)
                .orElseThrow(() -> new NotFoundException("No Refresh Token in Database"));

        // check if rf token expired
        if(refreshTokenUtil.isRefreshTokenExpired(staff))
            throw new RuntimeException("Refresh Token Expired, please login again");

        // generate new access token
        String newAccessToken = jwtTokenUtil.generateToken(staff);

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", newAccessToken);

        return map;
    }
}
