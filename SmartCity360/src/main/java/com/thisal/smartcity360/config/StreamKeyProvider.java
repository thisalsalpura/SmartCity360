package com.thisal.smartcity360.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.logging.Logger;

@ApplicationScoped
public class StreamKeyProvider {

    public static final String SECRET_PROPERTY = "smartcity360.stream.secret";

    public static final String SECRET_ENV = "SMARTCITY360_STREAM_SECRET";

    private static final int MIN_SECRET_BYTES = 32;

    private static final String AES = "AES";

    private static final String HMAC = "HmacSHA256";

    private static final Logger LOGGER = Logger.getLogger(StreamKeyProvider.class.getName());

    private SecretKey encryptionKey;

    private SecretKey authenticationKey;

    public StreamKeyProvider() {
    }

    public StreamKeyProvider(byte[] masterSecret) {
        initialise(masterSecret);
    }

    private static byte[] derive(byte[] masterSecret, String label) {
        try {
            Mac mac = Mac.getInstance(StreamKeyProvider.HMAC);
            mac.init(new SecretKeySpec(masterSecret, StreamKeyProvider.HMAC));
            return mac.doFinal(label.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Could not derive Stream Key!", e);
        }
    }

    private static byte[] loadMasterSecret() {
        String configured = System.getProperty(StreamKeyProvider.SECRET_PROPERTY, System.getenv(StreamKeyProvider.SECRET_ENV));
        if (configured != null && !configured.isBlank()) {
            return configured.getBytes(StandardCharsets.UTF_8);
        }
        LOGGER.warning("No Stream Secret Configured! (" + StreamKeyProvider.SECRET_PROPERTY + " / " + StreamKeyProvider.SECRET_ENV + ")\n" +
                "Using a random development Secret. Keys change of every Restart.");
        byte[] random = new byte[StreamKeyProvider.MIN_SECRET_BYTES];
        new SecureRandom().nextBytes(random);
        return random;
    }

    @PostConstruct
    void init() {
        initialise(loadMasterSecret());
    }

    public SecretKey encryptionKey() {
        return encryptionKey;
    }

    public SecretKey authenticationKey() {
        return authenticationKey;
    }

    private void initialise(byte[] masterSecret) {
        if (masterSecret == null || masterSecret.length < StreamKeyProvider.MIN_SECRET_BYTES) {
            throw new IllegalArgumentException("Stream Master Secret must be at least " + StreamKeyProvider.MIN_SECRET_BYTES + " Bytes!");
        }
        this.encryptionKey = new SecretKeySpec(derive(masterSecret, "smartcity360/stream/encryption"), StreamKeyProvider.AES);
        this.authenticationKey = new SecretKeySpec(derive(masterSecret, "smartcity360/stream/authentication"), StreamKeyProvider.HMAC);
    }

}