package de.rgse.brewlogs.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import de.rgse.brewlogs.configuration.DataSourceConfig;
import de.rgse.brewlogs.decorators.Brew;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@ApplicationScoped
@Path("/hello")
public class HelloWorldEndpoint {

	@Brew
	@Inject
	private DataSourceConfig datasource;
	
	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello from WildFly Swarm! " + datasource.getUserName()).build();
	}
}