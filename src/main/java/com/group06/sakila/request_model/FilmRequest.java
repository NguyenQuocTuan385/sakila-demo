package com.group06.sakila.request_model;

import com.group06.sakila.enums.ERating;
import com.group06.sakila.enums.ESpecialFeatures;
import com.group06.sakila.validation.EnumValidator;
import com.group06.sakila.validation.SetEnumValidator;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FilmRequest {
    @NotBlank(message = "Title firm is not blank")
    @NotNull(message = "Title film is required")
    @Size(min = 1, max = 255, message = "The length of title film must be between 1 and 255")
    private String title;

    private String description;

    @Max(value = 2155, message = "Release year must be less than 2155")
    @Min(value = 1901, message = "Release year must be greater than 1901")
    private Integer releaseYear;

    @NotNull(message = "Language id is required")
    @Min(value = 1, message = "Language id must be greater than 1")
    @Max(value = 255, message = "Language id must be greater than 255")
    private Integer languageId;

    @Min(value = 1, message = "Original language id must be greater than 1")
    @Max(value = 255, message = "Original language id must be greater than 255")
    private Integer originalLanguageId;

    @Min(value = 0, message = "Rental duration must be greater than 0")
    @Max(value = 255, message = "Rental duration must be greater than 255")
    @NotNull(message = "Rental duration is required")
    private Integer rentalDuration;

    @NotNull(message = "Rental rate is required")
    private Double rentalRate;

    @Min(value = 0, message = "Length must be greater than 0")
    private Integer length;

    @NotNull(message = "Replacement cost is required")
    private Double replacementCost;

    @EnumValidator(enumClass = ERating.class, message = "Rating value isn't valid")
    private String rating;

    @SetEnumValidator(enumClass = ESpecialFeatures.class, message = "Special features value aren't valid")
    private String specialFeatures;
}
