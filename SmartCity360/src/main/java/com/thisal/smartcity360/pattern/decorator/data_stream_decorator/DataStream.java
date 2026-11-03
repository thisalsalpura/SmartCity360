package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

public interface DataStream {

    byte[] send(byte[] payload);

    byte[] receive(byte[] frame);

    String describe();

}