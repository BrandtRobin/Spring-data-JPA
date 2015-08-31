package se.meer.jpa.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.meer.jpa.superclass.AbstractEntity;

@Entity
@Table(name = "tblTeams")
public class Team extends AbstractEntity {

	private String teamName;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Collection<User> users;

	public Team(String teamName) {
		this.teamName = teamName;
	}

	protected Team() {
	}

	public String getTeamName() {
		return teamName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
