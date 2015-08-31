package se.meer.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long>, WorkItemCustomRepository {
	
	List<WorkItem> findByWorkItemNumber(Long workItemNumber);
}
