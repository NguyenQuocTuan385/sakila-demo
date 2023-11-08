package com.group06.sakila.request_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StaffRegisterRequest {
    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @JsonProperty("address_id")
    @NotNull(message = "Address id is required")
    private Integer addressId;

    private String picture;

    private String email;

    @JsonProperty("store_id")
    @NotNull(message = "Store id is required")
    private Integer storeId;

    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 16, message = "email must be between 1 and 16")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
