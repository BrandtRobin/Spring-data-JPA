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

import se.meer.jpa.annotation.Secure;
import se.meer.jpa.model.Team;
import se.meer.jpa.service.TeamService;
import se.meer.jpa.service.UserService;

@Secure
@Path("teams")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TeamWebService {

	@Context
	private UriInfo uriInfo;

	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private static TeamService service = new TeamService();
	private static UserService userService = new UserService();
	
	static {
		context.scan("se.meer.jpa.config");
		context.refresh();
		service = context.getBean(TeamService.class);
		userService = context.getBean(UserService.class);
	}

	@POST
	public Response createTeam(final Team team) {
		service.createOrUpdateTeam(team);
		final String id = "id/" + team.getId();
		final URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.status(Status.CREATED).entity(team).location(location).build();
	}

	@DELETE
	@Path("id/{id}")
	public Response deleteTeamById(@PathParam("id") final Long id) {
		service.deleteTeamById(id);
		return Response.ok("Team with id " + id + " deleted").build();
	}
	
	@GET
	@Path("id/{id}")
	public Response findTeamById(@PathParam("id") final Long id) {
		Team team = service.findTeamById(id);
		return Response.ok().entity(team).build();
	}

	@PUT
	@Path("id/{id}")
	public Response updateTeamById(@PathParam("id") final Long id, final Team team) {
		Team teamUpdate = service.findByTeamId(id);
		if (teamUpdate == null) {
			return Response.status(Status.NOT_FOUND).entity("Team with id " + id + " not found").build();
		}
		teamUpdate.setTeamName(team.getTeamName());
		service.createOrUpdateTeam(team);
		return Response.ok().entity(team).build();
	}

	@GET
	public Response findAllTeams() {
		final List<Team> teams = service.findAllTeams();
		return Response.ok().entity(teams).build();
	}

	@PUT
	@Path("id/{teamId}/user/{userId}")
	public Response addUserToTeam(@PathParam("teamId") final Long teamId, @PathParam("userId") final Long userId) {
		if (service.findByTeamId(teamId) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else if (userService.findUserById(userId) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		service.addUserToTeam(userId, service.findByTeamId(teamId));
		return Response.ok().build();
	}
	
	@PUT
	@Path("id/{teamId}/username/{username}")
	public Response addUserToTeam(@PathParam("teamId") final Long teamId, @PathParam("username") final String username) {
		if (service.findByTeamId(teamId) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else if (userService.findUserByUsername(username) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		service.addUserToTeam(username, service.findByTeamId(teamId));
		return Response.ok().build();
	}
}
