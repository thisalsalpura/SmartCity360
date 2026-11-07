package com.thisal.smartcity360.exception;

import java.io.Serial;

public class PolicyEvaluationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PolicyEvaluationException(String message) {
        super(message);
    }

}