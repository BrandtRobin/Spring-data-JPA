package se.meer.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		userRepository.save(user);
		return userRepository.save(user);
	}

}
