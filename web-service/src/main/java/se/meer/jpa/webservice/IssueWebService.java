package se.meer.jpa.webservice;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import se.meer.jpa.model.Issue;
import se.meer.jpa.service.IssueService;

@Component
@Path("issues")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class IssueWebService {

	@Context
	private UriInfo uriInfo;

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private final IssueService service = getIssueService();

	@POST
	public Response createIssue(final Issue issue) {
		service.createOrUpdateIssue(issue);
		final String id = "id/" + issue.getId();
		final URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(location).build();
	}

	@PUT
	@Path("id/{id}")
	public Response updateIssueById(@PathParam("id") final Long id, final Issue issue) {
		Issue tempIssue = service.findIssueById(id);
		if (tempIssue != null) {
			issue.setWorkItem(tempIssue.getWorkItem());
			issue.setId(id);
			service.createOrUpdateIssue(issue);
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}

	private IssueService getIssueService() {
		context.scan("se.meer.jpa.config");
		context.refresh();
		IssueService service = context.getBean(IssueService.class);
		return service;
	}
}
