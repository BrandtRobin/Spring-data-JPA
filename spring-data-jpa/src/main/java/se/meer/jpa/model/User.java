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
import javax.xml.bind.annotation.XmlRootElement;

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

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	Collection<WorkItem> workItems;

	protected User() {
	}

	public User(String firstname, String lastname, String username) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
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
	
	public String getUserNumber()
	{
		return userNumber;
	}
	
	public void setUserNumber(String userNumber)
	{
		this.userNumber = userNumber;
	}
	
	public void addUserNumber()
	{
		this.userNumber = UUID.randomUUID().toString();
	}
	
	public Collection<WorkItem> getWorkItems()
	{
		return workItems;
	}
	
	public void setWorkItems(Collection<WorkItem> workItems)
	{
		this.workItems = workItems;
	}
}
