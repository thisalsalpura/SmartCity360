package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

import com.thisal.smartcity360.enums.StreamLayer;
import com.thisal.smartcity360.exception.StreamSecurityException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionDecorator extends DataStreamDecorator {

    static final int MAX_DECOMPRESSED_BYTES = 16 * 1024 * 1024;

    private static final int CHUNK_BYTES = 4096;

    public CompressionDecorator(DataStream wrapped) {
        super(wrapped);
    }

    @Override
    protected byte[] transformOutgoing(byte[] data) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.max(64, data.length / 2));
        try (GZIPOutputStream gzip = new GZIPOutputStream(buffer)) {
            gzip.write(data);
        } catch (IOException e) {
            throw new StreamSecurityException("Compression Failed!", e);
        }
        return buffer.toByteArray();
    }

    @Override
    protected byte[] transformIncoming(byte[] data) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] chunk = new byte[CompressionDecorator.CHUNK_BYTES];
        try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(data))) {
            int read;
            while ((read = gzip.read(chunk)) != -1) {
                if (result.size() + read > CompressionDecorator.MAX_DECOMPRESSED_BYTES) {
                    throw new StreamSecurityException("Decompression Rejected! Data expands beyond " + CompressionDecorator.MAX_DECOMPRESSED_BYTES + " Bytes.");
                }
                result.write(chunk, 0, read);
            }
        } catch (IOException e) {
            throw new StreamSecurityException("Decompression Failed! Data is corrupted or not GZIP.", e);
        }
        return result.toByteArray();
    }

    @Override
    protected String layerName() {
        return StreamLayer.COMPRESSION.getDisplayName();
    }

}