package se.meer.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User createOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}

	public List<User> findUserByFirstname(String firstname) {
		return userRepository.findByFirstname(firstname);
	}

	public List<User> findUserByLastname(String lastname) {
		return userRepository.findByLastname(lastname);
	}

	public List<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findUserByUserNo(String userNumber) {
		return userRepository.findUserByUserNumber(userNumber);
	}

	public List<User> findUsersByTeam(Team team) {
		List<User> users = new ArrayList<>();
		users.addAll(userRepository.findUserByTeam(team));
		return users;
	}

	public List<User> findUsersByTeamId(Long id) {
		List<User> users = new ArrayList<>();
		users.addAll(userRepository.findUserByTeamId(id));
		return users;
	}

	public Long deleteUserById(Long id) {
		userRepository.delete(id);
		return id;
	}

	public User deleteUser(User user) {
		userRepository.delete(user);
		return user;
	}
	
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}
