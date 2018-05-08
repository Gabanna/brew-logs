package de.rgse.brewlogs.api;


import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(Exception exception) {
        logger.log(Level.SEVERE, "error while processing rest call", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
