package se.meer.jpa.webservice;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;
import se.meer.jpa.service.UserService;

@Path("user")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class UserWebService
{

	@Context
	private UriInfo uriInfo;

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


//	@GET
//	@Path("{teamName}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response findUserByTeam(@PathParam("teamName") final String teamName)
//	{
//		final ArrayList<User> usersInTeam = getUserService().findUsersByTeam(teamName);
//		return Response.ok().entity(usersInTeam).build();
//	}
//	
//	@GET
//	@Path("{firstname}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response findUserByFirstname(@PathParam("firstname") final String firstname)
//	{
//		final ArrayList<User> users = getUserService().findUserByFirstname(firstname);
//		return Response.ok().entity(users).build();
//	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response findUserById(@PathParam("id") final Long id)
	{
		//final ArrayList<User>
		final User user = getUserService().findUserById(id);
		return Response.ok().entity(user.getId()).build();
	}
	

	public UserService getUserService()
	{
		context.scan("se.meer.jpa.config");
		context.refresh();
		UserService service = context.getBean(UserService.class);
		return service;
	}
}
