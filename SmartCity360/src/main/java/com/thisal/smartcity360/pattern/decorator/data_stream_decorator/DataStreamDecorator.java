package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

import java.util.Objects;

public abstract class DataStreamDecorator implements DataStream {

    protected final DataStream wrapped;

    protected DataStreamDecorator(DataStream wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped, "Wrapped data stream cannot be Null!");
    }

    @Override
    public byte[] send(byte[] payload) {
        return wrapped.send(transformOutgoing(payload));
    }

    @Override
    public byte[] receive(byte[] frame) {
        return transformIncoming(wrapped.receive(frame));
    }

    @Override
    public String describe() {
        return layerName() + " -> " + wrapped.describe();
    }

    protected abstract byte[] transformOutgoing(byte[] data);

    protected abstract byte[] transformIncoming(byte[] data);

    protected abstract String layerName();

}