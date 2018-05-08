package de.rgse.brewlogs.api;

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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UserEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private JwtService jwtService;

    @POST
    @Validate
    public Response register(@Validatable RegisterVo register){
        try {
            User user = userService.registerUser(register);
            return Response.ok(new UserVo(user)).build();

        } catch(UserExistsException | UsernameEmailTakenException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN_TYPE).entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response login(LoginVo login) {
        return null;
    }
}
