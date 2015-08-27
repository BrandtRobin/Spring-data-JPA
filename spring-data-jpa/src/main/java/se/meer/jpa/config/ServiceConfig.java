package se.meer.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.meer.jpa.service.UserService;

@Configuration
public class ServiceConfig {

	@Bean
	public UserService userService() {
		return new UserService();
	}
}
