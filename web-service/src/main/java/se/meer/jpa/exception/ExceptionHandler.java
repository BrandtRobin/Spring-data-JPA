package se.meer.jpa.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(final Exception exception) {
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
	}

}