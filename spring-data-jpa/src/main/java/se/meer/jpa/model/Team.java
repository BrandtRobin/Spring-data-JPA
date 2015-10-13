package se.meer.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tblTeams")
public class Team extends AbstractEntity {

	private String teamName;

	protected Team() {
	}

	public Team(String name) {
		this.teamName = name;
	}

	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
