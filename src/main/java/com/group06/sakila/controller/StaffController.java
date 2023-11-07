package com.group06.sakila.controller;

import com.group06.sakila.entity.Staff;
import com.group06.sakila.request_model.StaffLoginRequest;
import com.group06.sakila.request_model.StaffRegisterRequest;
import com.group06.sakila.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping("/register")
    ResponseEntity<Staff> register(@RequestBody @Valid StaffRegisterRequest staffRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.createStaff(staffRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody StaffLoginRequest staffLogin) {
        try {
//            String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(staffService.login(staffLogin.getEmail(), staffLogin.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
