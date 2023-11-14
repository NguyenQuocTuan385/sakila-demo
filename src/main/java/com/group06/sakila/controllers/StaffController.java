package com.group06.sakila.controllers;

import com.group06.sakila.request_models.StaffLoginRequest;
import com.group06.sakila.request_models.StaffRefreshRequest;
import com.group06.sakila.request_models.StaffRegisterRequest;
import com.group06.sakila.services.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/staffs")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Valid StaffRegisterRequest staffRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(staffService.createStaff(staffRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody StaffLoginRequest staffLogin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(staffService.login(staffLogin.getUsername(), staffLogin.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody StaffRefreshRequest staffRefresh) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(staffService.refresh(staffRefresh.getRefreshToken()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
