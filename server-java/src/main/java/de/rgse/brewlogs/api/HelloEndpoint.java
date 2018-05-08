package de.rgse.brewlogs.api;

import de.rgse.brewlogs.process.BrewLog;
import org.camunda.bpm.engine.ProcessEngine;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class HelloEndpoint {

    @Inject
    @BrewLog
    private ProcessEngine processEngine;

    @GET
    @Path("hello")
    public Response sayHello() {
        return Response.ok("hello").build();
    }


    @GET
    @Path("engine")
    public Response showEngine() {
        return Response.ok(processEngine.getName()).build();
    }
}

