package com.negrea.csf.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CannotSaveWishException extends RuntimeException{
    private String message;

    public CannotSaveWishException(String message) {
        super(message);
        this.message = message;
    }
}
