package com.thisal.smartcity360.exception;

import java.io.Serial;

public class InvalidFleetOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFleetOperationException(String message) {
        super(message);
    }

}