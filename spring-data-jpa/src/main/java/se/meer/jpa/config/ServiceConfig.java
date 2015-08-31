package se.meer.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.meer.jpa.model.WorkItem;
import se.meer.jpa.service.TeamService;
import se.meer.jpa.service.UserService;
import se.meer.jpa.service.WorkItemService;

@Configuration
public class ServiceConfig {

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public TeamService teamService() {
		return new TeamService();
	}
	
	@Bean
	public WorkItemService workItemService() {
		return new WorkItemService();
	}
}
