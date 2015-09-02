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

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Collection<User> users;

	protected Team() {
	}

	public String getTeamName() {
		return teamName;
	}

	public Team(String name) {
		this.teamName = name;
	}

	public void addUser(User user) {
		users.add(user);
	}

}
