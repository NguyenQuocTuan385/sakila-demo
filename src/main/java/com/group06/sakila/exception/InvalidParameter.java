package com.group06.sakila.exception;

public class InvalidParameter extends RuntimeException {
    public InvalidParameter(String message) {
        super(message);
    }
}
