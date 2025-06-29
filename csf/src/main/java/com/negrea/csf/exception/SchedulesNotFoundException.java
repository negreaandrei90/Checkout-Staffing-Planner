package com.negrea.csf.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SchedulesNotFoundException extends RuntimeException {
    private String message;

    public SchedulesNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
