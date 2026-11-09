package com.thisal.smartcity360.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class PolicyParseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int position;

    public PolicyParseException(String message, int position) {
        super(message);
        this.position = position;
    }

}