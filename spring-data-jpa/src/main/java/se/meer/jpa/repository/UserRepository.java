package se.meer.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findUserByUsername(String username);

	@Query()
	List<User> findUserByTeam(Team team);
	User findByFirstname(String firstname);
	User findByLastname(String lastname);
	User findByUsername(String username);

}
