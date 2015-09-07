package se.meer.jpa.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblTeams")
public class Team extends AbstractEntity {

	private String teamName;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<User> users;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<WorkItem> workItems;

	protected Team() {
	}

	public Team(String name) {
		this.teamName = name;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void addWorkItem(WorkItem workItem) {
		workItems.add(workItem);
	}
}
