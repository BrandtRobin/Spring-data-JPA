package se.meer.jpa.webservice;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import se.meer.jpa.model.Team;
import se.meer.jpa.service.TeamService;

@Component
@Path("team")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TeamWebService {

	@Context
	private UriInfo uriInfo;

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private final TeamService service = getTeamService();

	@POST
	public Response createTeam(final Team team) {
		service.createOrUpdateTeam(team);
		final String id = "id/" + team.getId();
		final URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.status(Status.CREATED).location(location).build();
	}

	@DELETE
	@Path("id/{id}")
	public Response deleteTeamById(@PathParam("id") final Long id) {
		service.deleteTeamById(id);
		return Response.ok("Team with id " + id + "deleted").build();
	}

	@PUT
	@Path("id/{id}")
	public Response updateTeamById(@PathParam("id") final Long id, final Team team) {
		team.setId(id);
		service.createOrUpdateTeam(team);
		return Response.ok().entity(team).build();
	}

	@GET
	public Response findAllTeams(@PathParam("teamName") final Team team) {
		final List<Team> teams = service.findAllTeams();
		return Response.ok().entity(teams).build();
	}

	@PUT
	@Path("id/{teamId}/user/{userId}")
	public Response addUserToTeam(@PathParam("teamId") final Long teamId, @PathParam("userId") final Long userId) {
		service.addUserToTeam(userId, service.findByTeamId(teamId));
		return Response.ok().entity(null).build();
	}

	private TeamService getTeamService() {
		context.scan("se.meer.jpa.config");
		context.refresh();
		TeamService service = context.getBean(TeamService.class);
		return service;
	}
}
