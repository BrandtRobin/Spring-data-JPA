package se.meer.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
			
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			IssueService issueService = context.getBean(IssueService.class);
			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			
			WorkItem workItem = new WorkItem("hej", "Hejsan", "Hejsahoppas");
			WorkItem workItem2 = new WorkItem("hej", "Hejsan", "Hejsahoppas");
			WorkItem workItem3 = new WorkItem("hej", "Hejsan", "Hejsahoppas");
			WorkItem workItem4 = new WorkItem("hej", "Hejsan", "Hejsahoppas");
			
			workItemService.createOrUpdateWorkItem(workItem);
			workItemService.createOrUpdateWorkItem(workItem2);
			workItemService.createOrUpdateWorkItem(workItem3);
			workItemService.createOrUpdateWorkItem(workItem4);
			
		}
	}
}
