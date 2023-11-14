package com.group06.sakila.component;



import com.group06.sakila.entities.Staff;
import com.group06.sakila.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class RefreshTokenUtil {
    private final JwtTokenUtil jwtTokenUtil;
    private final StaffRepository staffRepository;

    public String generateRefreshToken(String jwtToken, Staff currentStaff){
        LocalDateTime refreshTokenIssuedTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        String rfToken = jwtTokenUtil.hashToken(jwtToken);

        // hash then set token for existingStaff entity
        currentStaff.setRefreshToken(rfToken);
        currentStaff.setRefreshTokenExpiration(refreshTokenIssuedTime.plusDays(7)); // expired after 7 day

        // save refresh token to DB
        staffRepository.save(currentStaff);

        return rfToken;
    }

    public Boolean isRefreshTokenExpired(Staff staff){
        return staff.getRefreshTokenExpiration().isBefore(LocalDateTime.now());
    }
}
