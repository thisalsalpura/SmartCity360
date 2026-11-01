package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

import com.thisal.smartcity360.enums.StreamLayer;
import com.thisal.smartcity360.exception.StreamSecurityException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Objects;

public class AuthenticationDecorator extends DataStreamDecorator {

    private static final String ALGORITHM = "HmacSHA256";

    private static final int TAG_BYTES = 32;

    private final SecretKey key;

    public AuthenticationDecorator(DataStream wrapped, SecretKey key) {
        super(wrapped);
        this.key = Objects.requireNonNull(key, "Authentication key must not be Null!");
    }

    @Override
    protected byte[] transformOutgoing(byte[] data) {
        byte[] tag = sign(data);
        byte[] frame = new byte[data.length + AuthenticationDecorator.TAG_BYTES];
        System.arraycopy(data, 0, frame, 0, data.length);
        System.arraycopy(tag, 0, frame, data.length, AuthenticationDecorator.TAG_BYTES);
        return frame;
    }

    @Override
    protected byte[] transformIncoming(byte[] data) {
        if (data.length < AuthenticationDecorator.TAG_BYTES) {
            throw new StreamSecurityException("Authentication Failed! Frame is too short to carry a Signature.");
        }
        int bodyLength = data.length - AuthenticationDecorator.TAG_BYTES;
        byte[] body = Arrays.copyOfRange(data, 0, bodyLength);
        byte[] receivedTag = Arrays.copyOfRange(data, bodyLength, data.length);
        if (!MessageDigest.isEqual(receivedTag, sign(body))) {
            throw new StreamSecurityException("Authentication Failed! Wrong key or frame was Altered.");
        }
        return body;
    }

    @Override
    protected String layerName() {
        return StreamLayer.AUTHENTICATION.getDisplayName();
    }

    private byte[] sign(byte[] data) {
        try {
            Mac mac = Mac.getInstance(AuthenticationDecorator.ALGORITHM);
            mac.init(key);
            return mac.doFinal(data);
        } catch (GeneralSecurityException e) {
            throw new StreamSecurityException("Signing Failed!", e);
        }
    }

}