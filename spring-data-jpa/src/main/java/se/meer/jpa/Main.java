package se.meer.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.service.TeamService;
import se.meer.jpa.service.UserService;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();
			
		
			
			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			Team team = new Team("Dev");
			User user = new User("Eric", "Guru", "EriGu", team);
			teamService.addTeam(team);
			userService.addUser(user);
		}
	}

}
