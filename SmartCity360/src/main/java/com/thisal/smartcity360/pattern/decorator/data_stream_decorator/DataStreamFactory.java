package com.thisal.smartcity360.pattern.decorator.data_stream_decorator;

import com.thisal.smartcity360.config.StreamKeyProvider;
import com.thisal.smartcity360.enums.StreamLayer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Objects;

@ApplicationScoped
public class DataStreamFactory {

    private final StreamKeyProvider keys;

    protected DataStreamFactory() {
        this.keys = null;
    }

    @Inject
    public DataStreamFactory(StreamKeyProvider keys) {
        this.keys = Objects.requireNonNull(keys, "Key Provider must not be Null!");
    }

    public DataStream create(String meterId, Collection<StreamLayer> requested) {
        EnumSet<StreamLayer> layers = EnumSet.noneOf(StreamLayer.class);
        layers.addAll(requested);

        DataStream stream = new EnergyMeterDataStream(meterId);

        StreamLayer[] all = StreamLayer.values();
        for (int i = all.length - 1; i >= 0; i--) {
            if (layers.contains(all[i])) {
                stream = decorate(stream, all[i]);
            }
        }
        return stream;
    }

    private DataStream decorate(DataStream inner, StreamLayer layer) {
        return switch (layer) {
            case COMPRESSION -> new CompressionDecorator(inner);
            case ENCRYPTION -> {
                assert keys != null;
                yield new EncryptionDecorator(inner, keys.encryptionKey());
            }
            case AUTHENTICATION -> {
                assert keys != null;
                yield new AuthenticationDecorator(inner, keys.authenticationKey());
            }
        };
    }

}