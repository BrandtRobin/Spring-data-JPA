package se.meer.jpa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

			Team team = new Team("Development");
			User user = new User("Göran", "Eriksson", "masterguru");
			Issue issue = new Issue("This must be fixed", "Bug is being tested");
			WorkItem workItem = new WorkItem("This must be fixed", "Error in code", "DONE!");
			
			workItemService.findByDescriptionContaining("code").forEach(System.out::println);;

			// Create User
			// userService.addUser(user);

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

			// Add WorkItem to User
			// workItem.addUser(user);
			// workItemService.createOrUpdateWorkItem(workItem);

			// Find WorkItem by Status
			// workItemService.createOrUpdateWorkItem(workItem);
			// workItemService.findByStatus("DONE!").forEach(System.out::println);

			// Find WorkItem by Team
			// workItemService.createOrUpdateWorkItem(workItem);
			// workItemService.findByTeam(team).forEach(System.out::println);

			// Update Issue
			// issueService.updateIssue(1L, issue);

			// issueService.findIssueById(1L);

			// teamService.addUserToTeam(1L, teamService.findByTeamId(2L));
			// teamService.addUserToTeam(3L, teamService.findByTeamId(2L));
			// teamService.addUserToTeam(4L, teamService.findByTeamId(2L));
			// teamService.addUserToTeam(5L, teamService.findByTeamId(2L));
			// teamService.addUserToTeam(6L, teamService.findByTeamId(2L));
			//
			// Find WorkItem by User
			// workItemService.createOrUpdateWorkItem(workItem);
			// workItemService.findByUser(user).forEach(System.out::println);

			// Find WorkItem by Description
			// workItemService.findByDescription("%error%", new PageRequest(0,
			// 10)).forEach(System.out::println);

			// Create Issue
			// issue.addWorkItem(workItem);
			// issueService.createOrUpdateIssue(issue);

			// Update Issue
			// issue.setDescription("This is now fixed");
			// issue.setTitle("Finished testing");
			// issueService.createOrUpdateIssue(issue);

			// Find all WorkItems with Issue
			// workItemService.findWorkItemsWithIssue().forEach(System.out::println);

		}
	}
}
