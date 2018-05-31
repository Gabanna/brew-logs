package de.rgse.brewlogs.api;

import de.rgse.brewlogs.api.util.JwtSecured;
import de.rgse.brewlogs.api.vo.BrewLogVo;
import de.rgse.brewlogs.api.vo.ErrorVo;
import de.rgse.brewlogs.api.vo.TaskVo;
import de.rgse.brewlogs.domain.BrewLog;
import de.rgse.brewlogs.services.BrewLogService;
import de.rgse.brewlogs.services.JwtService;
import de.rgse.brewlogs.services.ProcessService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("brew-logs")
public class BrewLogEndpoint extends AbstractEndpoint {

    @Inject
    private Logger logger;

    @Inject
    private JwtService jwtService;

    @Inject
    private BrewLogService brewLogService;

    @Inject
    private ProcessService processService;

    @POST
    @Transactional
    @JwtSecured
    public Response createBrewLog(@NotNull @HeaderParam(HttpHeaders.AUTHORIZATION) String token, BrewLog brewLog) {
        try {
            Optional<Claims> jwt = jwtService.validateJwt(token);
            long userId = jwt.get().get("id", Long.class);
            brewLogService.persist(brewLog, userId);
            processService.startProcess(brewLog.getId());

            return Response.status(Response.Status.CREATED).entity(BrewLogVo.of(brewLog)).build();

        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (JwtException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "unable to create brew log", e);
            return Response.serverError().entity(new ErrorVo(e)).build();
        }
    }

    @GET
    @JwtSecured
    @Path("user/{username}")
    public Response findBrewLogsByUser(@NotNull @PathParam("username") String username) {
        try {
            List<BrewLog> brewLogs = brewLogService.findByUserName(username);
            return Response.ok(BrewLogVo.of(brewLogs)).build();

        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (Exception e) {
            ErrorVo error = new ErrorVo(e);
            logger.log(Level.SEVERE, error.getLogMessage(), error.getThrowable());
            return Response.serverError().entity(error).build();
        }
    }

    @GET
    @JwtSecured
    @Path("{brewLogId}/process")
    public Response findBrewLogProcess(@PathParam("brewLogId") long brewLogId) {
        try {
            List<TaskVo> tasks = processService.findTasksByLog(brewLogId);
            return Response.ok(tasks).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (Exception e) {
            ErrorVo error = new ErrorVo(e);
            logger.log(Level.SEVERE, error.getLogMessage(), error.getThrowable());
            return Response.serverError().entity(error).build();
        }
    }


    @DELETE
    @JwtSecured
    @Transactional
    @Path("{brewLogId}")
    public Response deleteBrewLog(@PathParam("brewLogId") long brewLogId) {
        try {
            brewLogService.removeById(brewLogId);
            processService.deleteProcessForLog(brewLogId);
            return Response.ok().build();

        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (Exception e) {
            ErrorVo error = new ErrorVo(e);
            logger.log(Level.SEVERE, error.getLogMessage(), error.getThrowable());
            return Response.serverError().entity(error).build();
        }
    }
}
