package com.negrea.csf.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WishNotFoundException extends RuntimeException {
    private String message;

    public WishNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
