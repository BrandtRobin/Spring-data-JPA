package se.meer.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.meer.jpa.config");
			context.refresh();

			// Find by description
			// workItemService.findByDescriptionContaining("code").forEach(System.out::println);;

			// Find all WorkItems with Issue
			// workItemService.findWorkItemsWithIssue().forEach(System.out::println);

		}
	}
}
