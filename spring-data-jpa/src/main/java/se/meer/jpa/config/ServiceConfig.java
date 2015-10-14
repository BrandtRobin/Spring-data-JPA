package se.meer.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.meer.jpa.service.IssueService;
import se.meer.jpa.service.TeamService;
import se.meer.jpa.service.TokenService;
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

	@Bean
	public IssueService issueService() {
		return new IssueService();
	}
	
	@Bean
	public TokenService tokenService() {
		return new TokenService();
	}
}
