package se.meer.jpa.exception;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 7713888559949526077L;

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(String message) {
		super(message);
	}
}
