package se.meer.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.repository.UserRepository;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();

			Team team = new Team("Development");

			User user = new User("Eric", "Guru", "EriGu", team);
			UserRepository userRepository = context.getBean(UserRepository.class);
			userRepository.save(user);

		}
	}

}
