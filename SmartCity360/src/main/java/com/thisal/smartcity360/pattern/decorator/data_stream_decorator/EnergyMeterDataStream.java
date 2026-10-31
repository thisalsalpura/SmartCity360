package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

import java.util.Objects;

public class EnergyMeterDataStream implements DataStream {

    private static final int MAX_FRAME_BYTES = 10 * 1024 * 1024;

    private final String meterId;

    public EnergyMeterDataStream(String meterId) {
        if (meterId == null || meterId.isBlank()) {
            throw new IllegalArgumentException("MeterId must not be Null or Blank!");
        }
        this.meterId = meterId;
    }

    public static void requiredValidFrame(byte[] data, String name) {
        Objects.requireNonNull(data, name + " must not be Null!");
        if (data.length == 0) {
            throw new IllegalArgumentException(name + " must not be Empty!");
        } else if (data.length > EnergyMeterDataStream.MAX_FRAME_BYTES) {
            throw new IllegalArgumentException(name + " must not exceed " + EnergyMeterDataStream.MAX_FRAME_BYTES + " Bytes!");
        }
    }

    @Override
    public byte[] send(byte[] payload) {
        requiredValidFrame(payload, "Payload");
        return payload.clone();
    }

    @Override
    public byte[] receive(byte[] frame) {
        requiredValidFrame(frame, "Frame");
        return frame.clone();
    }

    @Override
    public String describe() {
        return "EnergyMeterDataStream: MeterId=" + this.meterId;
    }

}