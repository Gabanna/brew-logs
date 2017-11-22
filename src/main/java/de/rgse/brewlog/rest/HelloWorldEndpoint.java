package de.rgse.brewlog.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.querydsl.jpa.impl.JPAQuery;

import de.rgse.brewlog.domain.BrewLog;
import de.rgse.brewlog.domain.QBrewLog;
import de.rgse.brewlog.repository.BrewLogRepository;
import de.rgse.brewlog.repository.Specification;

@RequestScoped
@Path("/hello")
public class HelloWorldEndpoint {

	@Inject
	private BrewLogRepository brewLogRepository;
	
	@GET
	@Produces("text/plain")
	public Response doGet() {
		List<BrewLog> result = brewLogRepository.query(new Specification<BrewLog>() {
			
			@Override
			public JPAQuery<BrewLog> getQuery() {
				return new JPAQuery<BrewLog>().from(QBrewLog.brewLog);
			}
		});
		
		System.out.println(result);
		
		return Response.ok("Hello from WildFly Swarm! ").build();
	}
}