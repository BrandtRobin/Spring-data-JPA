package se.meer.jpa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaxxer.hikari.metrics.CodaHaleMetricsTracker.Context;

import se.meer.jpa.model.Issue;
import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.service.IssueService;
import se.meer.jpa.service.TeamService;
import se.meer.jpa.service.UserService;
import se.meer.jpa.service.WorkItemService;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();

			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			IssueService issueService = context.getBean(IssueService.class);

//			Team team = new Team("Development");
//			 User user = new User("Göran", "Eriksson",
//			 "masterguru").setTeam(teamService.findByTeamId(2L));
//			User user = new User("Eliashs", "Habibi", "Yalla");
//			Issue issue = new Issue("This must be fixed", "Bug is being tested by YODA");
//			WorkItem workItem = new WorkItem("Wont compile I THINK", "Bug", "Testing", user, issue);

//			 teamService.addTeam(team);
//			userService.addUser(user);
			// issueService.addIssue(issue);
			// workItemService.addWorkItem(workItem);

			// Create User
			// userService.addUser(user);
//			userService.deleteUserById(3L);

			// Update User
			// User tempUser = userService.findUserById(1L);
			// tempUser.setFirstname("Anton");
			// tempUser.setLastname("Sjögren");
			// tempUser.setUsername("mastersky");
			// userService.addUser(tempUser);

			// Delete User
			// userService.deleteUserById(1L);

			// Find User by Id
			// userService.findUserById(1L);

			// Find User by username, firstname & lastname
			// userService.findUserByFirstname("Göran");
			// userService.findUserByLastname("Eriksson");
			// userService.findUserByUsername("masterguru");

			// Find Users in team
			// List<User> tempUsers =
			// userService.findUsersByTeam(teamService.findByTeamId(1L));
			// tempUsers.forEach(System.out::println);

			// Delete Team
			// teamService.deleteTeamById(2L);

			// Get all Teams
			// List<Team> tempTeam = teamService.findAllTeams();
			// tempTeam.forEach(System.out::println);

			// Add User to Team
			// teamService.addUserToTeam(2L, team);

			// Delete WorkItem
			// workItemService.deleteWorkItemById(2L);

			// Update Issue
			// issueService.updateIssue(1L, issue);

			// issueService.findIssueById(1L);

		}
	}

}
