package com.negrea.csf.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NotEnoughEmployeesException extends RuntimeException{
    private String message;

    public NotEnoughEmployeesException(String message) {
        super(message);
        this.message = message;
    }
}
