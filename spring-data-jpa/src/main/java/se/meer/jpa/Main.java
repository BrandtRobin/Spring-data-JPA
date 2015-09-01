package se.meer.jpa;

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

			User user = new User("Göran", "Eriksson", "masterguru").setTeam(team);
			
			Issue issue = new Issue("This must be fixed", "Bug is being tested");

			WorkItem workItem = new WorkItem("Wont compile", "Bug", "Testing", user, issue);

			teamService.addTeam(team);
			userService.addUser(user);
			issueService.addIssue(issue);
			workItemService.addWorkItem(workItem);

		}
	}

}
