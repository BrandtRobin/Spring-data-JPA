package se.meer.jpa.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import se.meer.jpa.exception.RepositoryException;

@Provider
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException>
{

	@Override
	public Response toResponse(final RepositoryException exception)
	{
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
	}

}