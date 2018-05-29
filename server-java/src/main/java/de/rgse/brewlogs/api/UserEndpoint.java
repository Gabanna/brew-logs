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
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("users")
public class UserEndpoint extends AbstractEndpoint {

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
            String tokenJson = Json.createObjectBuilder().add("token", token).build().toString();
            return Response.status(Response.Status.CREATED).entity(tokenJson).header(HttpHeaders.AUTHORIZATION, tokenJson).build();

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
            String token = jwtService.createBearerToken(user.get());
            String tokenJson = Json.createObjectBuilder().add("token", token).build().toString();
            builder = Response.ok(tokenJson).header(HttpHeaders.AUTHORIZATION, tokenJson);

        } else {
            builder = Response.status(Response.Status.UNAUTHORIZED);

        }

        return builder.build();
    }
}
