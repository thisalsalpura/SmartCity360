package com.thisal.smartcity360.enums;

public enum StreamLayer {

    COMPRESSION("Compression (GZIP)", "Compresses the payload with GZIP to reduce Bandwidth."),
    ENCRYPTION("Encryption (AES-256-GCM)", "Encrypts the payload so only the city platform can read It."),
    AUTHENTICATION("Authentication (HMAC-SHA256)", "Signs the frame so tampering or forget frames are Rejected.");

    private final String displayName;
    private final String description;

    StreamLayer(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

}