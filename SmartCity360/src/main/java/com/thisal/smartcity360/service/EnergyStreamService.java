package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.DeliveryOutcome;
import com.thisal.smartcity360.enums.StreamLayer;
import com.thisal.smartcity360.exception.StreamSecurityException;
import com.thisal.smartcity360.pattern.decorator.data_stream_decorator.DataStream;
import com.thisal.smartcity360.pattern.decorator.data_stream_decorator.DataStreamFactory;
import com.thisal.smartcity360.record.MeterReading;
import com.thisal.smartcity360.record.StageSize;
import com.thisal.smartcity360.record.TransmissionReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.nio.charset.StandardCharsets;
import java.util.*;

@ApplicationScoped
public class EnergyStreamService {

    private static final int PREVIEW_CHARS = 160;

    private final DataStreamFactory factory;

    protected EnergyStreamService() {
        this.factory = null;
    }

    @Inject
    public EnergyStreamService(DataStreamFactory factory) {
        this.factory = Objects.requireNonNull(factory, "Data Stream Factory must not be Null!");
    }

    private static List<StreamLayer> inApplicationOrder(Collection<StreamLayer> requested) {
        EnumSet<StreamLayer> set = EnumSet.noneOf(StreamLayer.class);
        if (requested != null) {
            set.addAll(requested);
        }
        return List.copyOf(set);
    }

    private static byte[] encodePayload(String meterId, List<MeterReading> readings) {
        StringBuilder text = new StringBuilder("MeterId=").append(meterId).append("\n");
        for (MeterReading reading : readings) {
            text.append(reading.timestamp()).append(",").append(reading.kilowattHours()).append("\n");
        }
        return text.toString().getBytes(StandardCharsets.UTF_8);
    }

    private static String preview(byte[] received) {
        String text = new String(received, StandardCharsets.UTF_8);
        return text.length() <= EnergyStreamService.PREVIEW_CHARS
                ? text
                : text.substring(0, EnergyStreamService.PREVIEW_CHARS) + "...";
    }

    public List<StreamLayer> availableLayers() {
        return List.of(StreamLayer.values());
    }

    public TransmissionReport roundTrip(String meterId, List<MeterReading> readings, Collection<StreamLayer> requestedLayers, boolean tamper) {
        List<StreamLayer> layers = inApplicationOrder(requestedLayers);
        byte[] payload = encodePayload(meterId, readings);

        assert factory != null;
        DataStream stream = factory.create(meterId, layers);
        byte[] wire = stream.send(payload);

        byte[] delivered = wire.clone();
        if (tamper) {
            delivered[delivered.length / 2] ^= 0x01;
        }

        byte[] received;
        try {
            received = stream.receive(delivered);
        } catch (StreamSecurityException e) {
            if (!tamper) throw e;
            return report(
                    meterId,
                    stream,
                    readings.size(),
                    payload,
                    wire,
                    layers,
                    DeliveryOutcome.TAMPERING_REJECTED,
                    e.getMessage(),
                    ""
            );
        }

        boolean identical = Arrays.equals(payload, received);
        DeliveryOutcome outcome = identical
                ? DeliveryOutcome.DELIVERED_INTACT
                : DeliveryOutcome.TAMPERING_UNDETECTED;
        String message = identical
                ? "Frame Verified and Decoded! The payload is identical to what the meter Sent."
                : "No layer noticed the Modification! Corrupted data was accepted as Genuine.";
        return report(
                meterId,
                stream,
                readings.size(),
                payload,
                wire,
                layers,
                outcome,
                message,
                preview(received)
        );
    }

    private TransmissionReport report(
            String meterId,
            DataStream stream,
            int readingCount,
            byte[] payload,
            byte[] wire,
            List<StreamLayer> layers,
            DeliveryOutcome outcome,
            String message,
            String receivedPreview
    ) {
        return new TransmissionReport(
                meterId,
                stream.describe(),
                readingCount,
                payload.length,
                wire.length,
                stageSizes(meterId, payload, layers),
                outcome,
                message,
                receivedPreview
        );
    }

    private List<StageSize> stageSizes(String meterId, byte[] payload, List<StreamLayer> layers) {
        List<StageSize> stages = new ArrayList<>();
        stages.add(new StageSize("Original Payload", payload.length));
        for (int count = 1; count <= layers.size(); count++) {
            assert factory != null;
            byte[] frame = factory.create(meterId, layers.subList(0, count)).send(payload);
            stages.add(new StageSize(layers.get(count - 1).getDisplayName(), frame.length));
        }
        return stages;
    }

}