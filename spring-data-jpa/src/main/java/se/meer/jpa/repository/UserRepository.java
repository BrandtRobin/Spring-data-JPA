package se.meer.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserCustomRepository {

	List<User> findByFirstname(String firstname);
	List<User> findByLastname(String lastname);
	List<User> findByFirstnameAndLastname(String firstname, String lastname);

}
