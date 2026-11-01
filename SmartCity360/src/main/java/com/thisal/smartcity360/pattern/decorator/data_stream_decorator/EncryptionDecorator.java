package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

import com.thisal.smartcity360.enums.StreamLayer;
import com.thisal.smartcity360.exception.StreamSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Objects;

public class EncryptionDecorator extends DataStreamDecorator {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";

    private static final int IV_BYTES = 12;

    private static final int TAG_BYTES = 16;

    private final SecretKey key;

    private final SecureRandom random = new SecureRandom();

    public EncryptionDecorator(DataStream wrapped, SecretKey key) {
        super(wrapped);
        this.key = Objects.requireNonNull(key, "Encryption key must not be Null!");
    }

    @Override
    protected byte[] transformOutgoing(byte[] data) {
        byte[] iv = new byte[EncryptionDecorator.IV_BYTES];
        random.nextBytes(iv);
        try {
            Cipher cipher = Cipher.getInstance(EncryptionDecorator.TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(EncryptionDecorator.TAG_BYTES * 8, iv));
            byte[] encrypted = cipher.doFinal(data);

            byte[] frame = new byte[EncryptionDecorator.IV_BYTES + encrypted.length];
            System.arraycopy(iv, 0, frame, 0, EncryptionDecorator.IV_BYTES);
            System.arraycopy(encrypted, 0, frame, EncryptionDecorator.IV_BYTES, encrypted.length);
            return frame;
        } catch (GeneralSecurityException e) {
            throw new StreamSecurityException("Encryption Failed!", e);
        }
    }

    @Override
    protected byte[] transformIncoming(byte[] data) {
        if (data.length < EncryptionDecorator.IV_BYTES + EncryptionDecorator.TAG_BYTES) {
            throw new StreamSecurityException("Decryption Failed! Frame is too short to contain IV and Tag.");
        }
        try {
            Cipher cipher = Cipher.getInstance(EncryptionDecorator.TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(EncryptionDecorator.TAG_BYTES * 8, data, 0, EncryptionDecorator.IV_BYTES));
            return cipher.doFinal(data, EncryptionDecorator.IV_BYTES, data.length - EncryptionDecorator.IV_BYTES);
        } catch (GeneralSecurityException e) {
            throw new StreamSecurityException("Decryption Failed! Wrong key or frame was Altered.", e);
        }
    }

    @Override
    protected String layerName() {
        return StreamLayer.ENCRYPTION.getDisplayName();
    }

}