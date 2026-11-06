package com.thisal.smartcity360.web.rest.resource;

import com.thisal.smartcity360.model.LightCountRequest;
import com.thisal.smartcity360.model.ScheduleRequest;
import com.thisal.smartcity360.record.BenchmarkReport;
import com.thisal.smartcity360.record.FleetSummary;
import com.thisal.smartcity360.record.ScheduleReport;
import com.thisal.smartcity360.record.StreetLightTypeInfo;
import com.thisal.smartcity360.service.StreetLightService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Path("/street-lights")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StreetLightResource {

    private final StreetLightService service;

    protected StreetLightResource() {
        this.service = null;
    }

    @Inject
    public StreetLightResource(StreetLightService service) {
        this.service = Objects.requireNonNull(service, "Street Light Service must not be Null!");
    }

    @GET
    @Path("/types")
    public List<StreetLightTypeInfo> types() {
        assert service != null;
        return service.types();
    }

    @GET
    public FleetSummary fleet(@QueryParam("limit") @DefaultValue("20") int limit) {
        assert service != null;
        return service.fleet(limit);
    }

    @POST
    @Path("/generate")
    public FleetSummary generate(@NotNull @Valid LightCountRequest request) {
        assert service != null;
        return service.generate(request.getCount());
    }

    @POST
    @Path("/schedule")
    public ScheduleReport schedule(@NotNull @Valid ScheduleRequest request) {
        assert service != null;
        return service.applySchedule(LocalTime.parse(request.getTime()));
    }

    @POST
    @Path("/benchmark")
    public BenchmarkReport benchmark(@NotNull @Valid LightCountRequest request) {
        assert service != null;
        return service.benchmark(request.getCount());
    }

    @DELETE
    public FleetSummary reset() {
        assert service != null;
        return service.reset();
    }

}