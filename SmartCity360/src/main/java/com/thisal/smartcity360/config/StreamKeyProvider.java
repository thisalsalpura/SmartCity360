package com.thisal.smartcity360.config;

import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;
import java.util.logging.Logger;

@ApplicationScoped
public class StreamKeyProvider {

    public static final String SECRET_PROPERTY = "smartcity360.stream.secret";

    public static final String SECRET_ENV = "SMARTCITY360_STREAM_SECRET";

    private static final int MIN_SECRET_BYTES = 32;

    private static final String HMAC = "HmacSHA256";

    private static final Logger LOGGER = Logger.getLogger(StreamKeyProvider.class.getName());

    private SecretKey encryptionKey;

    private SecretKey authenticationKey;

}