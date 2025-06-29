package com.negrea.csf.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InvalidWishException extends RuntimeException {
    private String message;

    public InvalidWishException(String message) {
        super(message);
        this.message = message;
    }
}
