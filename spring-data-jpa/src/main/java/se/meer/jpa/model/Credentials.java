package se.meer.jpa.model;

import java.io.Serializable;

public class Credentials implements Serializable {
 
	private static final long serialVersionUID = 7998958050934116336L;
	private String username;
    private String password;
 
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
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
