package se.meer.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.WorkItem;
import se.meer.jpa.repository.WorkItemRepository;

public class WorkItemService {
	
	@Autowired
	private WorkItemRepository workItemRepository;

	public WorkItem addWorkItem(WorkItem workItem) {
		workItemRepository.save(workItem);
		return workItemRepository.save(workItem);
	}
}
