package com.thisal.smartcity360.web.rest.resource;

import com.thisal.smartcity360.enums.PolicyVariable;
import com.thisal.smartcity360.model.PolicyEvaluationRequest;
import com.thisal.smartcity360.model.PolicyRequest;
import com.thisal.smartcity360.model.RuleCheckRequest;
import com.thisal.smartcity360.record.*;
import com.thisal.smartcity360.service.PolicyService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Path("/policies")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PolicyResource {

    private final PolicyService service;

    protected PolicyResource() {
        this.service = null;
    }

    @Inject
    public PolicyResource(PolicyService service) {
        this.service = Objects.requireNonNull(service, "Policy Service must not be Null!");
    }

    private static PolicyContext toContext(PolicyEvaluationRequest request) {
        Map<PolicyVariable, Double> values = new EnumMap<>(PolicyVariable.class);
        if (request.getNoise() != null) {
            values.put(PolicyVariable.NOISE, request.getNoise());
        }
        if (request.getParkingHours() != null) {
            values.put(PolicyVariable.PARKING, request.getParkingHours());
        }
        if (request.getSpeed() != null) {
            values.put(PolicyVariable.SPEED, request.getSpeed());
        }
        return new PolicyContext(values, LocalTime.parse(request.getTime()));
    }

    @GET
    public List<PolicyView> list() {
        assert service != null;
        return service.list();
    }

    @GET
    @Path("/variables")
    public List<VariableInfo> variables() {
        assert service != null;
        return service.variables();
    }

    @POST
    public Response create(@NotNull @Valid PolicyRequest request) {
        assert service != null;
        PolicyView created = service.create(request.getName(), request.getDescription(), request.getRule());
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public PolicyView update(@PathParam("id") String id, @NotNull @Valid PolicyRequest request) {
        assert service != null;
        return service.update(id, request.getName(), request.getDescription(), request.getRule());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        assert service != null;
        service.delete(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/validate")
    public ValidationReport validate(@NotNull @Valid RuleCheckRequest request) {
        assert service != null;
        return service.validate(request.getRule());
    }

    @POST
    @Path("/{id}/evaluate")
    public EvaluationReport evaluate(@PathParam("id") String id, @NotNull @Valid PolicyEvaluationRequest request) {
        assert service != null;
        return service.evaluate(id, toContext(request));
    }

    @POST
    @Path("/evaluate-all")
    public List<EvaluationReport> evaluateAll(@NotNull @Valid PolicyEvaluationRequest request) {
        assert service != null;
        return service.evaluateAll(toContext(request));
    }

}