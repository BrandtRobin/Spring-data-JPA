package se.meer.jpa.webservice;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;

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

import se.meer.jpa.Hash;
import se.meer.jpa.model.Credentials;
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
	public Response authenticateUser(final Credentials loginUser) {

		System.out.println("PASSWORD " + service.findUserByUsername(loginUser.getUsername()).getPassword());
		try {

			Hash.validate(loginUser.getPassword(), service.findUserByUsername(loginUser.getUsername()).getPassword());

			String token = Hash.createHash(createTokenString());
			tokenMap.put(loginUser.getUsername(), token);
			
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
    public String createTokenString() {
	    Random random = new SecureRandom();
	    String token = new BigInteger(130, random).toString(32);
	    return token;
    }

}
