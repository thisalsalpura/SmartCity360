package com.thisal.smartcity360.exception;

import java.io.Serial;

public class PolicyNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PolicyNotFoundException(String message) {
        super(message);
    }

}