package com.negrea.csf.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAlreadyHasShiftException extends RuntimeException {
    private String message;

    public UserAlreadyHasShiftException(String message) {
        super(message);
        this.message = message;
    }
}
