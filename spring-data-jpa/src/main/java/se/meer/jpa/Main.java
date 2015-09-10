package se.meer.jpa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.model.WorkItem;
import se.meer.jpa.service.WorkItemService;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();
			
			WorkItemService service = context.getBean(WorkItemService.class);
			
			List<WorkItem> workItems = service.findAllWorkItemsByTeamId(4L);
			
			workItems.size();
			
			for(WorkItem workItem : workItems) {
				System.out.println(workItem.getTitle());
			}
			
		}
	}
}
