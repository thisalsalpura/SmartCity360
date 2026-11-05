package com.thisal.smartcity360.web.rest.resource;

import com.thisal.smartcity360.model.ComplaintRequest;
import com.thisal.smartcity360.record.ComplaintReport;
import com.thisal.smartcity360.record.HandlerInfo;
import com.thisal.smartcity360.service.ComplaintService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

@Path("/complaints")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComplaintResource {

    private final ComplaintService service;

    protected ComplaintResource() {
        this.service = null;
    }

    @Inject
    public ComplaintResource(ComplaintService service) {
        this.service = Objects.requireNonNull(service, "Complaint Service must not be Null!");
    }

    @GET
    @Path("/handlers")
    public List<HandlerInfo> handlers() {
        assert service != null;
        return service.availableHandlers();
    }

    @GET
    public List<ComplaintReport> history() {
        assert service != null;
        return service.history();
    }

    @GET
    @Path("/{id}")
    public ComplaintReport find(@PathParam("id") String id) {
        assert service != null;
        return service.find(id);
    }

    @POST
    public Response submit(@NotNull @Valid ComplaintRequest request) {
        assert service != null;
        ComplaintReport report = service.submit(
                request.getResidentName(),
                request.getSubject(),
                request.getDescription(),
                request.getSeverity(),
                request.getOnDuty()
        );
        return Response.status(Response.Status.CREATED)
                .entity(report)
                .build();
    }

}