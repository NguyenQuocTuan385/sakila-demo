package com.group06.sakila.exceptions;

public class InvalidParameter extends RuntimeException {
    public InvalidParameter(String message) {
        super(message);
    }
}
