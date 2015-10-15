package se.meer.jpa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Token extends AbstractEntity implements Serializable {

	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String authToken;
	private LocalDateTime timeStamp;

	public Token() {
	}

	public Token(String username, String authToken) {
		this.username = username;
		this.authToken = "REEM " + authToken;
		this.timeStamp = LocalDateTime.now();

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
}