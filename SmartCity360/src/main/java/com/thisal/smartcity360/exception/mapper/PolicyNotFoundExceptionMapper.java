package com.thisal.smartcity360.exception.mapper;

import com.thisal.smartcity360.exception.PolicyNotFoundException;
import com.thisal.smartcity360.record.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PolicyNotFoundExceptionMapper implements ExceptionMapper<PolicyNotFoundException> {

    @Override
    public Response toResponse(PolicyNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), exception.getMessage()))
                .build();
    }

}