package com.group06.sakila.service;

import com.group06.sakila.component.JwtTokenUtil;
import com.group06.sakila.entity.Staff;
import com.group06.sakila.exception.NotFoundException;
import com.group06.sakila.repository.StaffRepository;
import com.group06.sakila.request_model.StaffRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final PasswordEncoder passwordEncoder;
    private final StaffRepository staffRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public Staff createStaff(StaffRegisterRequest staffRequest) {
        String email = staffRequest.getEmail();
        if (staffRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        Staff newStaff = Staff.builder()
                .firstName(staffRequest.getFirstName())
                .lastName(staffRequest.getLastName())
                .addressId(staffRequest.getAddressId())
                .email(staffRequest.getEmail())
                .storeId(staffRequest.getStoreId())
                .name(staffRequest.getUsername())
                .picture(staffRequest.getPicture())
                .password(staffRequest.getPassword())
                .active(true)
                .build();
        String password = staffRequest.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        newStaff.setPassword(encodePassword);
        return staffRepository.save(newStaff);
    }

    @Override
    public String login(String email, String password) throws Exception {
        Optional<Staff> optionalStaff = staffRepository.findByEmail(email);
        if (optionalStaff.isEmpty())
            throw new NotFoundException("Invalid username / password");
        Staff existingStaff = optionalStaff.get();
        if (!passwordEncoder.matches(password, existingStaff.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, password,
                existingStaff.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingStaff);
    }
}
