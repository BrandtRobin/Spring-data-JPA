package se.meer.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findTeamByTeamName(String teamName);
}
