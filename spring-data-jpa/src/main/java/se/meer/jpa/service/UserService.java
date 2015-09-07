package se.meer.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}

	public List<User> findUserByFirstname(String firstname) {
		return (List<User>) userRepository.findByFirstname(firstname);
	}

	public List<User> findUserByLastname(String lastname) {
		return (List<User>) userRepository.findByLastname(lastname);
	}

	public List<User> findUserByUsername(String username) {
		return (List<User>) userRepository.findByUsername(username);
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

	public User updateUserById(Long id, User user) {
		User userToUpdate = userRepository.findOne(id);
		userToUpdate.setFirstname(user.getFirstname());
		userToUpdate.setLastname(user.getLastname());
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setUserNumber(user.getUserNumber());
		userToUpdate.setTeam(user.getTeam());
		userToUpdate.setWorkItems(user.getWorkItems());
		return userRepository.save(userToUpdate);
	}

}
