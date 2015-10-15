package se.meer.jpa.model;

import javax.persistence.Entity;

@Entity
public class Token extends AbstractEntity {
	private String token;

	public Token(String token) {
		this.token = token;
	}

	protected Token() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}