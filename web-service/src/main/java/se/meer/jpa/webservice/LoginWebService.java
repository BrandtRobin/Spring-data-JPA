package se.meer.jpa.webservice;

import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.PasswordHash;
import se.meer.jpa.model.Credentials;
import se.meer.jpa.model.Token;
import se.meer.jpa.service.UserService;

@Path("login")
public class LoginWebService {

	@Context
	private UriInfo uriInfo;
	
	final HashMap<String, String> tokenMap = new HashMap();
	
	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private static UserService service = new UserService();
	
	static {
		context.scan("se.meer.jpa.config");
		context.refresh();
		service = context.getBean(UserService.class);
		
	}
	
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response authenticateUser(Credentials credentials) {

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        try {

            // Authenticate the user using the credentials provided
            PasswordHash.validatePassword(password, service.findPasswordByUsername(username));	

            // Issue a token for the user
            Token token = new Token(username, UUID.randomUUID().toString());

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    
}
