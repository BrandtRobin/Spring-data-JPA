package se.meer.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
	
	List<WorkItem> findByTitle(String title);
	List<WorkItem> findByDescription(String description);
	List<WorkItem> findByStatus(String status);
}
