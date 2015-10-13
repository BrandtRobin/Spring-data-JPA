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
import org.springframework.dao.EmptyResultDataAccessException;

import se.meer.jpa.model.Issue;
import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.service.IssueService;
import se.meer.jpa.service.TeamService;
import se.meer.jpa.service.UserService;
import se.meer.jpa.service.WorkItemService;

@Path("workitems")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

public class WorkItemWebService {

	@Context
	private UriInfo uriInfo;

	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private static WorkItemService service = new WorkItemService();
	private static UserService userService = new UserService();
	private static IssueService issueService = new IssueService();
	private static TeamService teamService = new TeamService();
	
	static {
		context.scan("se.meer.jpa.config");
		context.refresh();
		service = context.getBean(WorkItemService.class);
		userService = context.getBean(UserService.class);
		issueService = context.getBean(IssueService.class);
		teamService = context.getBean(TeamService.class);
	}

	@POST
	public Response createWorkItem(final WorkItem workItem) {
		service.createOrUpdateWorkItem(workItem);
		final String id = "id/" + workItem.getId();
		final URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.status(Status.CREATED).location(location).build();
	}

	@DELETE
	@Path("id/{id}")
	public Response deleteWorkItemById(@PathParam("id") final Long id) {
		try {
			service.deleteWorkItemById(id);
			return Response.ok().build();
		} catch (EmptyResultDataAccessException e) {
			return Response.status(Status.NOT_FOUND).entity("Could not find workItem with id: " + id).build();
		}
	}

	@GET
	@Path("id/{id}")
	public Response findWorkItemById(@PathParam("id") final Long id) {
		final WorkItem workItem = service.findWorkItemById(id);
		return Response.ok().entity(workItem).build();
	}

	@GET
	@Path("user/{id}")
	public Response findAllWorkItemsByUser(@PathParam("id") final Long userId) {
		List<WorkItem> workItems = service.findAllWorkItemsByUserId(userId);
		return Response.ok().entity(workItems).build();
	}

	@GET
	@Path("status/{status}")
	public Response findWorkItemByStatus(@PathParam("status") final String status) {
		final List<WorkItem> workItems = service.findByStatus(status);
		return Response.ok().entity(workItems).build();
	}

	@GET
	@Path("description/{description}")
	public Response findWorkItemByDescription(@PathParam("description") final String description) {
		final List<WorkItem> workItems = (List<WorkItem>) service.findByDescriptionContaining(description);
		return Response.ok().entity(workItems).build();
	}

	@GET
	@Path("team/{id}")
	public Response findAllWorkItemsByTeamId(@PathParam("id") final Long teamId) {
		List<WorkItem> workItems = service.findAllWorkItemsByTeamId(teamId);
		return Response.ok().entity(workItems).build();
	}

	@GET
	@Path("issues")
	public Response findWorkItemsWithIssue() {
		List<WorkItem> workItems = service.findWorkItemsWithIssue();
		return Response.status(Status.OK).entity(workItems).build();
	}

	@PUT
	@Path("id/{workItemId}/user/{userId}")
	public Response addWorkItemToUser(@PathParam("workItemId") final Long workItemId,
			@PathParam("userId") final Long userId) {
		if (service.findWorkItemById(workItemId) != null && (userService.findUserById(userId)) != null) {
			WorkItem workItem = service.findWorkItemById(workItemId);
			User user = userService.findUserById(userId);
			workItem.setUser(user);
			workItem.addTeam(user.getTeam());
			service.createOrUpdateWorkItem(workItem);
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}

	@PUT
	@Path("id/{id}")
	public Response updateWorkItemById(@PathParam("id") final Long id, WorkItem workItem) {
		WorkItem workItemUpdate = service.findWorkItemById(id);
		if(workItemUpdate != null) {
			workItemUpdate.setDescription(workItem.getDescription());
			workItemUpdate.setStatus(workItem.getStatus());
			workItemUpdate.setTitle(workItem.getTitle());
		}
		service.updateWorkItemById(id, workItemUpdate);
		return Response.ok().entity(workItem).build();
	}
	
	//TODO: CHANGE NAME TO addStatusToWorkItem
	@PUT
	@Path("id/{id}/status")
	public Response updateIssueOnWorkItem(@PathParam("id") final Long id, String status) {
		WorkItem workItem = service.findWorkItemById(id);
		workItem.setStatus(status);
		service.createOrUpdateWorkItem(workItem);
		return Response.ok().build();
	}
	
	@PUT
	@Path("id/{workItemId}/team/{teamId}")
	public Response addTeamToWorkItem(@PathParam("workItemId") final Long workItemId, 
			@PathParam("teamId") final Long teamId) {
		WorkItem workItem = service.findWorkItemById(workItemId);
		Team team = teamService.findByTeamId(teamId);
		workItem.addTeam(team);
		service.createOrUpdateWorkItem(workItem);
		return Response.ok().build();
	}

	@PUT
	@Path("id/{workItemId}/issue/{issueId}")
	public Response addIssueToWorkItem(@PathParam("workItemId") final Long workItemId,
			@PathParam("issueId") final Long issueId) {
		WorkItem workItem = service.findWorkItemById(workItemId);
		Issue issue = issueService.findIssueById(issueId);

		workItem.setIssue(issue);

		issueService.createOrUpdateIssue(issue);
		service.createOrUpdateWorkItem(workItem);
		return Response.ok().build();
	}
}