package com.thisal.smartcity360.web.rest.resource;

import com.thisal.smartcity360.model.ZoneCreateRequest;
import com.thisal.smartcity360.record.PowerActionReport;
import com.thisal.smartcity360.record.ZoneView;
import com.thisal.smartcity360.service.CityZoneService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.Objects;

@Path("/city-zones")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CityZoneResource {

    private final CityZoneService service;

    protected CityZoneResource() {
        this.service = null;
    }

    @Inject
    public CityZoneResource(CityZoneService service) {
        this.service = Objects.requireNonNull(service, "CityZone Service must not be Null!");
    }

    @GET
    public ZoneView tree() {
        assert service != null;
        return service.tree();
    }

    @GET
    @Path("/{id}/usage")
    public Map<String, Object> usage(@PathParam("id") String id) {
        assert service != null;
        return Map.of("zoneId", id, "kilowattHours", service.usage(id));
    }

    @POST
    @Path("/{id}/shutdown")
    public PowerActionReport shutdown(@PathParam("id") String id) {
        assert service != null;
        return service.shutdownPower(id);
    }

    @POST
    @Path("/{id}/restore")
    public PowerActionReport restore(@PathParam("id") String id) {
        assert service != null;
        return service.restorePower(id);
    }

    @POST
    public Response add(@NotNull @Valid ZoneCreateRequest request) {
        assert service != null;
        ZoneView created = service.addZone(request.getParentId(), request.getLevel(), request.getName(), request.getKilowattHours());
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") String id) {
        assert service != null;
        service.removeZone(id);
        return Response.noContent().build();
    }

}