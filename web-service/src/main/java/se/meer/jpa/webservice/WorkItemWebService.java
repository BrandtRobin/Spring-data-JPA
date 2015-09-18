package se.meer.jpa.webservice;

import java.net.URI;
import java.time.LocalDate;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import se.meer.jpa.model.Issue;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.service.IssueService;
import se.meer.jpa.service.UserService;
import se.meer.jpa.service.WorkItemService;

@Path("workitems")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

public class WorkItemWebService {

	@Context
	private UriInfo uriInfo;

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private final WorkItemService service = getWorkItemService();
	private final UserService userService = getUserService();
	private final IssueService issueService = getIssueService();

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
			return Response.status(Status.OK).entity("WorkItem with id " + id + " Deleted").build();
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
			workItem.addUser(userService.findUserById(userId));
			workItem.addTeam(userService.findUserById(userId).getTeam());
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
		if (workItemUpdate != null) {
			workItemUpdate.setDescription(workItem.getDescription());
			workItemUpdate.setStatus(workItem.getStatus());
			workItemUpdate.setTitle(workItem.getTitle());
		}
		service.updateWorkItemById(id, workItem);
		return Response.ok().entity(workItem).build();
	}

	@PUT
	@Path("id/{id}/status")
	public Response updateIssueOnWorkItem(@PathParam("id") final Long id, String status) {
		WorkItem workItem = service.findWorkItemById(id);
		workItem.setStatus(status);
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

	@GET
	@Path("{status}/{dateFrom}/{dateTo}")
	public Response findByStatusAndDateRange(@PathParam("status") final String status,
											 @PathParam("dateFrom") final String dateFrom, 
											 @PathParam("dateTo") final String dateTo) {
		final List<WorkItem> workItems = service.findByStatusAndDateRange(status, LocalDate.parse(dateFrom),
				LocalDate.parse(dateTo));
		return Response.ok().entity(workItems).build();
	}
	
	@GET
	@Path("{size}/{page}")
	public Response findAll(@PathParam("page") final int page, @PathParam("size") final int size){
		final Page<WorkItem> workItems = service.findAll(new PageRequest(page, size));
		return Response.ok().entity(workItems.getContent()).build();
	}

	private WorkItemService getWorkItemService() {
		context.scan("se.meer.jpa.config");
		context.refresh();
		WorkItemService service = context.getBean(WorkItemService.class);
		return service;
	}

	private IssueService getIssueService() {
		IssueService issueService = context.getBean(IssueService.class);
		return issueService;
	}

	private UserService getUserService() {
		UserService userService = context.getBean(UserService.class);
		return userService;
	}

}