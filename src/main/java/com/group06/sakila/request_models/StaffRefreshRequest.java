package com.group06.sakila.request_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StaffRefreshRequest {
    @JsonProperty("refresh_token")
    @NotBlank(message = "refresh token is required")
    String refreshToken;
}
