package se.meer.jpa.webservice;

import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.PasswordHash;
import se.meer.jpa.service.UserService;

@Path("login")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class LoginWebService {

	@Context
	private UriInfo uriInfo;

	private static final HashMap<String, String> tokenMap = new HashMap<String, String>();

	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private static UserService service = new UserService();

	static {
		context.scan("se.meer.jpa.config");
		context.refresh();
		service = context.getBean(UserService.class);
	}

	@POST
	public Response authenticateUser(@HeaderParam("username") String username,
									 @HeaderParam("password") String password) {

		System.out.println("PASSWORD " + service.findUserByUsername(username).getPassword());
		try {

			PasswordHash.validatePassword(password, service.findUserByUsername(username).getPassword());

			tokenMap.put(username, UUID.randomUUID().toString());
			return Response.ok().build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

}