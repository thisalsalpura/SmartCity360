package com.thisal.smartcity360.exception;

import java.io.Serial;

public class ComplaintNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ComplaintNotFoundException(String message) {
        super(message);
    }

}