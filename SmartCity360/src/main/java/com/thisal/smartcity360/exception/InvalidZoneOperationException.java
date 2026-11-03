package com.thisal.smartcity360.exception;

import java.io.Serial;

public class InvalidZoneOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidZoneOperationException(String message) {
        super(message);
    }

}