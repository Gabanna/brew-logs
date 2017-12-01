package de.rgse.brewlog.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import de.rgse.brewlog.configuration.Configuration;
import de.rgse.brewlog.decorators.Brew;
import de.rgse.brewlog.domain.auth.User;
import de.rgse.brewlog.repository.UserRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.undertow.util.Headers;

@Path("auth")
public class AuthEndpoint {

	@Inject @Brew
	private UserRepository userRepository;
	
	@Inject @Brew
	private Configuration configuration;
	
	@POST
	@Path("{username}")
	public Response doLogin(User user) {
		ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED);

		try {
			Optional<User> found = userRepository.find(User.class, user.getId());
				
			if(found.isPresent() && found.get().equals(user)) {
				JwtBuilder jwt = found.get().getJwt();
				String apiKey = configuration.getApiKey();

				if(apiKey != null) {
					jwt.signWith(SignatureAlgorithm.HS512, apiKey);
				}
				
				builder = Response.status(Response.Status.NO_CONTENT).header(Headers.AUTHORIZATION_STRING, jwt.compact());
			}
		
		} catch(Exception exception) {
			exception.printStackTrace();
			builder = Response.serverError().entity(exception.getMessage());
		}

		return builder.build();
	}
	
	@PUT
	@Path("{username}")
	public Response createUser(User user) {
		ResponseBuilder builder = Response.noContent();

		try {
			userRepository.add(user);
			JwtBuilder jwt = user.getJwt();
			String apiKey = configuration.getApiKey();

			if(apiKey != null) {
				jwt.signWith(SignatureAlgorithm.HS512, apiKey);
			}
			
			builder = Response.status(Response.Status.NO_CONTENT).header(Headers.AUTHORIZATION_STRING, jwt.compact());
		
		
		} catch(Exception exception) {
			exception.printStackTrace();
			builder = Response.serverError().entity(exception.getLocalizedMessage());
		}

		return builder.build();
	}

}
