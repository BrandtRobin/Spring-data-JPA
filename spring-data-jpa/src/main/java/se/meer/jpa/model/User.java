package se.meer.jpa.model;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.meer.jpa.superclass.AbstractEntity;

@Entity
@Table(name = "tblUsers")
public class User extends AbstractEntity {

	private String firstname;
	private String lastname;
	private String userNumber;
	private String userName;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<WorkItem> workItems;

	@ManyToOne
	private Team team;

	public User(String firstname, String lastname, String userName, Team team) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.userNumber = UUID.randomUUID().toString();
		this.userName = userName;
		this.team = team;
	}

	protected User() {
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public Collection<WorkItem> getWorkItems() {
		return workItems;
	}

	public Team getTeam() {
		return team;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
