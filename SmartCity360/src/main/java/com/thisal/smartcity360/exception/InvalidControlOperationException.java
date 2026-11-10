package com.thisal.smartcity360.exception;

import java.io.Serial;

public class InvalidControlOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidControlOperationException(String message) {
        super(message);
    }

}