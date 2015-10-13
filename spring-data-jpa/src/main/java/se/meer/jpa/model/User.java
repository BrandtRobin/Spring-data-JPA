package se.meer.jpa.model;

import java.util.ArrayList;
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
	private String password;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<WorkItem> workItems;

	protected User() {
	}

	public User(String firstname, String lastname, String userName, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = userName;
		this.password = password;
		this.userNumber = UUID.randomUUID().toString();
		workItems = new ArrayList<>();
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

	public Collection<WorkItem> getWorkItems() {
		return workItems;
	}

	public User setTeam(Team team) {
		this.team = team;
		return this;
	}

	public WorkItem addWorkItem(WorkItem workItem) {
		workItems.add(workItem);
		return workItem;
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
	
	public void addPassword(String password) {
		this.password = password;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

//	public void setWorkItems(Collection<WorkItem> workItems) {
//		this.workItems = workItems;
//	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
