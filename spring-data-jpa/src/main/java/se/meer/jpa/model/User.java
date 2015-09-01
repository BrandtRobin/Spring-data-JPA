package se.meer.jpa.model;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	Collection<WorkItem> workItems;

	protected User() {
	}

	public User(String firstname, String lastname, String userName) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = userName;
		this.userNumber = UUID.randomUUID().toString();

	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUserName() {
		return username;
	}

	public Team getTeam() {
		return team;
	}

	public User setTeam(Team team) {
		this.team = team;
		return this;
	}

	public void addWorkItem(WorkItem item) {
		workItems.add(item);
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
}
