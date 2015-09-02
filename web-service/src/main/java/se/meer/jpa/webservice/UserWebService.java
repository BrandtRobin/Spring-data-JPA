package se.meer.jpa.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
	
	private UserService service = getUserService();
	
	
	@GET
	@Path("{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello()
	{
		User user = new User("Berit", "lastname", "emp");

		service.addUser(user);
		
		// try (AnnotationConfigApplicationContext context = new
		// AnnotationConfigApplicationContext()) {
		// context.scan("se.meer.jpa.config");
		// context.refresh();
		// UserService service = context.getBean(UserService.class);
		// service.addUser(user);
		//
		 return user.getFirstname();
		//
		// }

	}

	public UserService getUserService()
	{
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext())
		{
			context.scan("se.meer.jpa.config");
			context.refresh();
			UserService service = context.getBean(UserService.class);
			return service;
		}
	}
}
