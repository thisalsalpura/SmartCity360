package com.thisal.smartcity360.exception.mapper;

import com.thisal.smartcity360.exception.PolicyParseException;
import com.thisal.smartcity360.record.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class PolicyParseExceptionMapper implements ExceptionMapper<PolicyParseException> {

    @Override
    public Response toResponse(PolicyParseException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), exception.getMessage()))
                .build();
    }

}