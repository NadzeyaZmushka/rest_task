package com.epam.jmp.rest.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final String details;

    public UserNotFoundException(String message, String details) {
        super(message);
        this.details = details;
    }
}
