package se.meer.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>, TeamCustomRepository{
	
//	List<User> findByFirstname(String firstname);
//	List<User> findByLastname(String lastname);
//	List<User> findByFirstnameAndLastname(String firstname, String lastname);
	
	List<Team> findByTeamName(String teamName);
}

