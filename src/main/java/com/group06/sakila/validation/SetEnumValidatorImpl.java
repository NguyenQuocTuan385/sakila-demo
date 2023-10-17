package com.group06.sakila.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;

public class SetEnumValidatorImpl implements ConstraintValidator<SetEnumValidator, String> {
    private List<String> valueList = null;

    @Override
    public void initialize(SetEnumValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        Enum[] enumValArr = enumClass.getEnumConstants();

        for(Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String[] inputArray = value.split(",");

        Set<String> uniqueElements = new HashSet<>(Arrays.asList(inputArray));

        if (inputArray.length != uniqueElements.size()) {
            return false;
        }

        for (String element : inputArray) {
            if (!valueList.contains(element)) {
                return false;
            }
        }

        return true;
    }
}
