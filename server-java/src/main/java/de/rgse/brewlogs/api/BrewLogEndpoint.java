package de.rgse.brewlogs.api;

import de.rgse.brewlogs.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("brew-logs")
public class BrewLogEndpoint {

    @Inject
    private JwtService jwtService;

    @POST
    public Response createBrewLog(@HeaderParam("Authorization") String authHeader) {
        Optional<Claims> jwt = jwtService.validateJwt(authHeader);
        return Response.ok().build();
    }

    @GET
    @Path("user/{username}")
    public Response findBrewLogsByUser(@NotNull @HeaderParam("Authorization") String authHeader, @PathParam("username") String username) {
        Optional<Claims> jwt = jwtService.validateJwt(authHeader);
        return Response.ok().build();
    }
}
