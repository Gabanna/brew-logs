package de.rgse.brewlog.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

@Path("auth")
public class AuthEndpoint {

	@GET
	@Path("{username}")
	public Response doLogin(@Context HttpServletRequest request) throws OAuthSystemException, URISyntaxException {
		ResponseBuilder builder = Response.noContent();

		try {
			// dynamically recognize an OAuth profile based on request characteristic
			// (params,
			// method, content type etc.), perform validation
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

			// build OAuth response
			OAuthResponse resp = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND)
					.setCode("123").location("https://www.google.de").buildQueryMessage();

			builder = Response.status(resp.getResponseStatus()).location(new URI(resp.getLocationUri()));

			// if something goes wrong
		} catch (OAuthProblemException ex) {
			URI uri = new URI(ex.getRedirectUri());

			final OAuthResponse resp = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(ex)
					.location(uri.getPath()).buildQueryMessage();

			builder = Response.status(resp.getResponseStatus()).location(uri);

		} catch (OAuthSystemException e) {
			builder = Response.serverError().entity(e.getLocalizedMessage());
		}

		return builder.build();
	}

	@GET
	@Path("token")
	public Response getToken(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OAuthSystemException {

		ResponseBuilder builder = Response.noContent();

		OAuthTokenRequest oauthRequest = null;

		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		try {
			oauthRequest = new OAuthTokenRequest(request);

			// some code
			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();

			// some code
			OAuthResponse r = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).setAccessToken(accessToken)
					.setExpiresIn("3600").setRefreshToken(refreshToken).buildJSONMessage();

			builder = Response.status(r.getResponseStatus()).entity(r.getBody());

		} catch (OAuthProblemException ex) {
			OAuthResponse r = OAuthResponse.errorResponse(Response.Status.UNAUTHORIZED.getStatusCode()).error(ex).buildJSONMessage();
			builder = Response.status(r.getResponseStatus()).entity(r.getBody());
		}
		
		return builder.build();

	}
}
