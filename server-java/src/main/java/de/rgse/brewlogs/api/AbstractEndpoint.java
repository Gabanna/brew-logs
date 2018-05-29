package de.rgse.brewlogs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

public abstract class AbstractEndpoint {

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpServletRequest httpServletRequest;

    public Optional<String> getAuthToken() {
        return Optional.ofNullable(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
