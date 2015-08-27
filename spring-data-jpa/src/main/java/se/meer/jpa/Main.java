package se.meer.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;
import se.meer.jpa.service.UserService;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();

//			Team team = new Team("Development");

			UserService userService = context.getBean(UserService.class);
			User user = new User("Eric", "Guru", "EriGu");
			userService.addUser(user);
		}
	}

}
