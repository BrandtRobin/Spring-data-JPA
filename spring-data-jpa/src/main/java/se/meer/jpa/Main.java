package se.meer.jpa;

import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import se.meer.jpa.model.User;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.service.UserService;
import se.meer.jpa.service.WorkItemService;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();
			
			UserService userService = context.getBean(UserService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			
//			User user = new User("Olle", "Svensson", "Kungen");
//			
//			userService.createOrUpdateUser(user);
//			System.out.println(LocalDate.parse("2015-01-01"));
			
			Page<WorkItem> items = workItemService.findAll(new PageRequest(0, 10));//.forEach(System.out::println);
			
//			System.out.println(items.getContent());
			
			items.forEach(System.out::println);
//			workItemService.findByStatusAndDateRange("Active", LocalDate.parse("2015-01-01"), LocalDate.parse("2015-12-31")).forEach(System.out::println);
			
//			workItemService.findByCreatedBetween(LocalDate.parse("2015-01-01"), LocalDate.parse("2015-12-31")).forEach(System.out::println);
			
//			workItemService.findByDescriptionContaining("meth").forEach(System.out::println);
		}
	}
}
