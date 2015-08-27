package se.meer.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.meer.jpa.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
}
