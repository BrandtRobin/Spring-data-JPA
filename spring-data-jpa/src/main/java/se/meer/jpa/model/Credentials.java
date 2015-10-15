package se.meer.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Credentials extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected Credentials() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}