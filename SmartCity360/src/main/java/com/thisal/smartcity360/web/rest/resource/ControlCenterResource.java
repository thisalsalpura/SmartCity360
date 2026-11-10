package com.thisal.smartcity360.web.rest.resource;

import com.thisal.smartcity360.model.NoiseMeasurementRequest;
import com.thisal.smartcity360.model.OutageRequest;
import com.thisal.smartcity360.model.ScheduleRequest;
import com.thisal.smartcity360.record.IncidentReport;
import com.thisal.smartcity360.record.ModuleInfo;
import com.thisal.smartcity360.service.ControlCenterService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Path("/control-center")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControlCenterResource {

    private final ControlCenterService service;

    protected ControlCenterResource() {
        this.service = null;
    }

    @Inject
    public ControlCenterResource(ControlCenterService service) {
        this.service = Objects.requireNonNull(service, "Control Center Service must not be Null!");
    }

    @GET
    @Path("/modules")
    public List<ModuleInfo> modules() {
        assert service != null;
        return service.modules();
    }

    @POST
    @Path("/outage")
    public IncidentReport outage(@NotNull @Valid OutageRequest request) {
        assert service != null;
        return service.reportOutage(request.getZoneId());
    }

    @POST
    @Path("/noise")
    public IncidentReport noise(@NotNull @Valid NoiseMeasurementRequest request) {
        assert service != null;
        return service.measureNoise(request.getNoise(), LocalTime.parse(request.getTime()));
    }

    @POST
    @Path("/time")
    public IncidentReport time(@NotNull @Valid ScheduleRequest request) {
        assert service != null;
        return service.changeTime(LocalTime.parse(request.getTime()));
    }

}