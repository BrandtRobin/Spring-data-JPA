package se.meer.jpa.model;

import java.io.Serializable;

public class Token implements Serializable {

	private String authId;
	private String authToken;

	public Token() {
	}

	public Token(String authId, String authToken) {
		this.authId = authId;
		this.authToken = authToken;

	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
