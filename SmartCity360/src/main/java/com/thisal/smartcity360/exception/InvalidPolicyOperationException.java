package com.thisal.smartcity360.exception;

import java.io.Serial;

public class InvalidPolicyOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPolicyOperationException(String message) {
        super(message);
    }

}