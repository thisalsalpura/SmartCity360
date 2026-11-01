package com.thisal.smartcity360.exception;

import java.io.Serial;

public class StreamSecurityException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public StreamSecurityException(String message) {
        super(message);
    }

    public StreamSecurityException(String message, Throwable cause) {
        super(message, cause);
    }

}