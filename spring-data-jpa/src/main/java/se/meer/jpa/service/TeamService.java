package se.meer.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Team;
import se.meer.jpa.repository.TeamRepository;

public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public Team addTeam(Team team) {
		teamRepository.save(team);
		return teamRepository.save(team);
	}

}
