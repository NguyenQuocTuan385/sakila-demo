package com.group06.sakila.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SuccessResponse {
    private int status;
    private String message;
    private Object data;

    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
