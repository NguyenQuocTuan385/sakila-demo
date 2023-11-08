package com.group06.sakila.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private Object errorDetails;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
