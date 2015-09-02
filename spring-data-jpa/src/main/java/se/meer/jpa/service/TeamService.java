package se.meer.jpa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.repository.TeamRepository;
import se.meer.jpa.repository.UserRepository;

public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UserRepository userRepository;

	public Team addTeam(Team team) {
		teamRepository.save(team);
		return teamRepository.save(team);
	}

	public Team findByTeamId(long id) {
		return teamRepository.findOne(id);
	}

	public Long deleteTeamById(long id) {
		List<User> users = new ArrayList<>();
		users.addAll(userRepository.findUserByTeamId(id));
		for (User user : users) {
			user.setTeam(null);
			userRepository.save(user);
		}
		teamRepository.delete(id);
		return id;
	}

	public List<Team> findAllTeams(){
		List<Team> teams = new ArrayList<>();
		teams.addAll((Collection<? extends Team>) teamRepository.findAll());
		return teams;
	}
	
	public void addUserToTeam(Long userId, Team team){
		User user = userRepository.findOne(userId);
		user.setTeam(team);
		userRepository.save(user);
	}
}
