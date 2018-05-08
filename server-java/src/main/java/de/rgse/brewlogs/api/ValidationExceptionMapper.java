package de.rgse.brewlogs.api;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<javax.validation.ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        StringBuilder message = new StringBuilder();
        exception.getConstraintViolations().forEach(violation -> {
            JsonObjectBuilder jsonObject = Json.createObjectBuilder();
            jsonObject.add("message", String.format("%s %s", violation.getPropertyPath(), violation.getMessage()));
            jsonArrayBuilder.add(jsonObject.build());
        });

        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).entity(jsonArrayBuilder.build().toString()).build();
    }
}
