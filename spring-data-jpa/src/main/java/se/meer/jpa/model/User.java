package se.meer.jpa.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import se.meer.jpa.Hash;

@Entity
@Table(name = "tblUsers")
public class User extends AbstractEntity {

	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column(unique = true)
	private String userNumber;
	@Column
	private String username;
	private String password;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "team_id")
	private Team team;

	protected User() {
	}

	public User(String firstname, String lastname, String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = userName;
		this.password = setPassword(password);
		this.userNumber = UUID.randomUUID().toString();
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUsername() {
		return username;
	}

	public Team getTeam() {
		return team;
	}

	public User setTeam(Team team) {
		this.team = team;
		return this;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addUserNumber() {
		this.userNumber = UUID.randomUUID().toString();
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return this.password = Hash.createHash(password);
	}

}
