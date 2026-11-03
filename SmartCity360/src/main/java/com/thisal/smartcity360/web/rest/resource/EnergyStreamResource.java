package com.thisal.smartcity360.web.rest.resource;

import com.thisal.smartcity360.enums.StreamLayer;
import com.thisal.smartcity360.model.RoundTripRequest;
import com.thisal.smartcity360.record.LayerInfo;
import com.thisal.smartcity360.record.MeterReading;
import com.thisal.smartcity360.record.TransmissionReport;
import com.thisal.smartcity360.service.EnergyStreamService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Objects;

@Path("/energy-stream")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnergyStreamResource {

    private final EnergyStreamService service;

    protected EnergyStreamResource() {
        this.service = null;
    }

    @Inject
    public EnergyStreamResource(EnergyStreamService service) {
        this.service = Objects.requireNonNull(service, "Energy Stream Service must not be Null!");
    }

    @GET
    @Path("/layers")
    public List<LayerInfo> layers() {
        assert service != null;
        return service.availableLayers().stream()
                .map(layer -> new LayerInfo(layer.name(), layer.getDisplayName(), layer.getDescription(), layer.ordinal() + 1))
                .toList();
    }

    @POST
    @Path("/round-trip")
    public TransmissionReport roundTrip(@NotNull @Valid RoundTripRequest request) {
        List<MeterReading> readings = request.getReadings().stream()
                .map(dto -> new MeterReading(dto.getTimestamp(), dto.getKilowattHours()))
                .toList();
        List<StreamLayer> layers = request.getLayers() == null
                ? List.of()
                : request.getLayers();
        assert service != null;
        return service.roundTrip(request.getMeterId(), readings, layers, request.isTamper());
    }

}