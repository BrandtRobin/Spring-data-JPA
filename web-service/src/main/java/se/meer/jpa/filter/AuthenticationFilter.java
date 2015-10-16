package se.meer.jpa.filter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import se.meer.jpa.Hash;
import se.meer.jpa.annotation.Secure;
import se.meer.jpa.webservice.LoginWebService;

@Secure
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String path = requestContext.getUriInfo().getPath();
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		String username = requestContext.getHeaderString("username");
		
		if (!path.startsWith("login")) {

			if (authorizationHeader == null || !authorizationHeader.startsWith("REEM ")) {
				throw new NotAuthorizedException("Authorization header must be provided");
			}

			String token = authorizationHeader.substring("REEM".length()).trim();
			
			if (!validateToken(token, username)) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		}
	}

	private boolean validateToken(String token, String username) {
		String hashedToken = LoginWebService.getTokenmap().get(username);
		try {
			return Hash.validatePassword(token, hashedToken);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return false;
		}
	}
}
