package com.group06.sakila.request_model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StaffLoginRequest {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
