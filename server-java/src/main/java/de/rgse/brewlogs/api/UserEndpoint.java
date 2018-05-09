package de.rgse.brewlogs.api;

import de.rgse.brewlogs.api.util.MediaTypes;
import de.rgse.brewlogs.api.vo.LoginVo;
import de.rgse.brewlogs.api.vo.RegisterVo;
import de.rgse.brewlogs.api.vo.UserVo;
import de.rgse.brewlogs.domain.User;
import de.rgse.brewlogs.exceptions.UserExistsException;
import de.rgse.brewlogs.exceptions.UsernameEmailTakenException;
import de.rgse.brewlogs.services.JwtService;
import de.rgse.brewlogs.services.UserService;
import de.rgse.brewlogs.services.Validatable;
import de.rgse.brewlogs.services.Validate;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("users")
public class UserEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private JwtService jwtService;

    @Transactional
    @POST
    @Validate
    @Consumes(MediaTypes.APPLICATION_JSON_UTF8)
    @Produces(MediaTypes.TEXT_PLAIN)
    public Response register(@NotNull @Validatable RegisterVo register) {
        try {
            User user = userService.registerUser(register);
            String token = jwtService.createBearerToken(user);
            return Response.status(Response.Status.CREATED).entity(token).header(HttpHeaders.AUTHORIZATION, token).build();

        } catch (UserExistsException | UsernameEmailTakenException e) {
            return Response.status(Response.Status.CONFLICT).type(MediaType.TEXT_PLAIN_TYPE).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Validate
    @Consumes(MediaTypes.APPLICATION_JSON_UTF8)
    public Response login(@NotNull @Validatable LoginVo login) {
        Optional<User> user = userService.login(login);

        Response.ResponseBuilder builder;
        if(user.isPresent()) {
            builder = Response.ok().header(HttpHeaders.AUTHORIZATION, jwtService.createBearerToken(user.get()));

        } else {
            builder = Response.status(Response.Status.UNAUTHORIZED);

        }

        return builder.build();
    }
}
