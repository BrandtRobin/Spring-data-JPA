package se.meer.jpa.webservice;

import java.math.BigInteger;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.Hash;
import se.meer.jpa.annotation.Secure;
import se.meer.jpa.model.Credentials;
import se.meer.jpa.model.Token;
import se.meer.jpa.model.User;
import se.meer.jpa.service.UserService;

@Secure
@Path("login")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class LoginWebService {

	@Context
	private UriInfo uriInfo;

	private static final HashMap<String, String> tokenMap = new HashMap<>();
	private static final String TOKEN_PREFIX = "REEM ";

	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private static UserService service = new UserService();

	static {
		context.scan("se.meer.jpa.config");
		context.refresh();
		service = context.getBean(UserService.class);
	}

	@POST
	public Response authenticateUser(final Credentials credentials)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			if (Hash.validate(credentials.getPassword(),
					service.findUserByUsername(credentials.getUsername()).getPassword())) {

				String tokenString = createTokenString();
				Token token = new Token(tokenString);
				tokenMap.put(credentials.getUsername(), Hash.createHash(token.getToken()));
				token.setToken(TOKEN_PREFIX + tokenString);
				return Response.ok(token).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	@POST
	@Path("/new-user")
	public Response createUser(final User user) {
		user.addUserNumber();
		service.createOrUpdateUser(user);
		final String id = "id/" + user.getId();
		final URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.status(Status.CREATED).location(location).build();
	}

	public String createTokenString() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Random random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		return token;
	}

	public static HashMap<String, String> getTokenmap() {
		return tokenMap;
	}
}
