package se.meer.jpa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findUserByUsername(String username);

	List<User> findUserByTeam(Team team);

	List<User> findByFirstname(String firstname);

	List<User> findByLastname(String lastname);

	List<User> findByUsername(String username);

	Collection<? extends User> findUserByTeamId(Long id);

	User findUserByUserNumber(String userNumber);

}
