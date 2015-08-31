package se.meer.jpa.repository;

import se.meer.jpa.model.Team;

public class TeamRepositoryImpl implements TeamCustomRepository {

	@Override
	public void logTeam(Team team) {
		System.out.println("0 # Logging employee: [" + team + "]");
	}
}
