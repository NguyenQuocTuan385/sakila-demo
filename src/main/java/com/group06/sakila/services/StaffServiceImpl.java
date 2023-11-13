package com.group06.sakila.services;

import com.group06.sakila.entities.Staff;
import com.group06.sakila.exceptions.NotFoundException;
import com.group06.sakila.repositories.StaffRepository;
import com.group06.sakila.request_models.StaffRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

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
        return staffRepository.save(newStaff);
    }

    @Override
    public Staff login(String username, String password) throws Exception {
        Optional<Staff> optionalStaff = staffRepository.findByUsername(username);
        if (optionalStaff.isEmpty())
            throw new NotFoundException("Invalid username / password");
        Staff existingStaff = optionalStaff.get();
        return existingStaff;
    }
}
