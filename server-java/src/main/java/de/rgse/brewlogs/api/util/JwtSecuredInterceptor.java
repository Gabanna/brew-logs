package de.rgse.brewlogs.api.util;

import de.rgse.brewlogs.api.AbstractEndpoint;
import de.rgse.brewlogs.services.JwtService;
import io.jsonwebtoken.JwtException;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@JwtSecured
@Interceptor
public class JwtSecuredInterceptor {

    @Inject
    private Logger logger;

    @Context
    private HttpServletRequest httpServletRequest;

    @Inject
    private JwtService jwtService;

    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {
        if(invocationContext.getTarget() instanceof AbstractEndpoint) {
            AbstractEndpoint abstractEndpoint = (AbstractEndpoint) invocationContext.getTarget();
            Optional<String> authToken = abstractEndpoint.getAuthToken();
            if(authToken.isPresent()) {
                try {
                    jwtService.validateJwt(authToken.get());

                } catch(JwtException e) {
                    logger.log(Level.WARNING, "invalid jwt found", e);
                    Response.status(Response.Status.UNAUTHORIZED);
                }

            } else {
                throw new IllegalStateException("no auth header found");
            }
        }
        return invocationContext.proceed();
    }
}
