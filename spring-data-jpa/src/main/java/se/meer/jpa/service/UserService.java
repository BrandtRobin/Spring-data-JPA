package se.meer.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User findUserById(Long id) {
		return userRepository.findOne(id);
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

	public List<User> findUserByFirstname(String firstname) {
		return userRepository.findByFirstname(firstname);
	}

	public List<User> findUserByLastname(String lastname) {
		return userRepository.findByLastname(lastname);
	}

	public List<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findUsersByTeam(Team team){
		List<User> users = new ArrayList<>();
		users.addAll(userRepository.findUserByTeam(team));
		return users;
	}
	
	public List<User> findUsersByTeamId(Long id) {
		List<User> users = new ArrayList<>();
		users.addAll(userRepository.findUserByTeamId(id));
		return users;
	}
	
	@Transactional
	public Long deleteUserById(Long id) {
		userRepository.delete(id);
		return id;
	}

	public User deleteUser(User user) {
		userRepository.delete(user);
		return user;
	}
	

}
