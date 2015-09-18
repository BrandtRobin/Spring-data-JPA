package se.meer.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.meer.jpa.service.UserService;
import se.meer.jpa.service.WorkItemService;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();
			
			UserService userService = context.getBean(UserService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			

		}
	}
}
