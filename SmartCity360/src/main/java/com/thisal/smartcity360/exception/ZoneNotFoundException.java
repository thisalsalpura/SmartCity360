package com.thisal.smartcity360.exception;

import java.io.Serial;

public class ZoneNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ZoneNotFoundException(String message) {
        super(message);
    }

}