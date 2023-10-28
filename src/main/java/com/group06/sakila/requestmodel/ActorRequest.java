package com.group06.sakila.requestmodel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorRequest {
    @NotBlank(message = "First name must be not blank")
    @Size(min = 1, max = 45, message = "First name must be between 1 and 45")
    private String firstName;

    @NotBlank(message = "Last name must be not blank")
    @Size(min = 1, max = 45, message = "Last name must be between 1 and 45")
    private String lastName;
}
