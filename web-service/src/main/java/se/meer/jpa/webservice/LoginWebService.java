package se.meer.jpa.webservice;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.PasswordHash;
import se.meer.jpa.model.Credentials;
import se.meer.jpa.model.Token;
import se.meer.jpa.model.User;
import se.meer.jpa.service.UserService;

@Path("login")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class LoginWebService {

	@Context
	private UriInfo uriInfo;
	
	final HashMap<String, Token> tokenMap = new HashMap();
	
	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private static UserService service = new UserService();
	
	static {
		context.scan("se.meer.jpa.config");
		context.refresh();
		service = context.getBean(UserService.class);
		
	}
	
    @POST
    public Response authenticateUser(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println("PASSWORD " + service.findUserByUsername(username).getPassword());
        try {

            // Authenticate the user using the credentials provided
            PasswordHash.validatePassword(password, service.findUserByUsername(username).getPassword());	

            // Issue a token for the user
//            Token token = new Token(username, "token");

            // Return the token on the response
            return Response.ok(user).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }
    
}
