package se.meer.jpa.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private String username;

//	@ManyToOne
//	private Team team;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<WorkItem> workItems;

	protected User() {
	}

	public User(String firstname, String lastname, String username) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
//		this.team = team;
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

//	public Team getTeam() {
//		return team;
//	}

	public Collection<WorkItem> getWorkItems() {
		return workItems;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
