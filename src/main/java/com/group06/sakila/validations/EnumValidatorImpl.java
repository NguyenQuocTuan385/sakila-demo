package com.group06.sakila.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private List<String> valueList = null;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return valueList.contains(value);
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        Enum[] enumValArr = enumClass.getEnumConstants();

        for(Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString());
        }
    }

}