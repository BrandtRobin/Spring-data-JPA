package se.meer.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.repository.WorkItemRepository;

public class WorkItemService {

	@Autowired
	private WorkItemRepository workItemRepository;

	public WorkItem createOrUpdateWorkItem(WorkItem workItem) {
		return workItemRepository.save(workItem);
	}

	public WorkItem findWorkItemById(Long id) {
		return workItemRepository.findOne(id);
	}

	public Long deleteWorkItemById(Long id) {
		workItemRepository.delete(id);
		return id;
	}

	public WorkItem updateWorkItemById(Long id, WorkItem workItem) {
		return workItemRepository.save(workItem);
	}

	public List<WorkItem> findByStatus(String status) {
		return workItemRepository.findByStatus(status);
	}

	public List<WorkItem> findAllWorkItemsByUserId(Long id) {
		return workItemRepository.findAllWorkItemsByUserId(id);
	}

	public List<WorkItem> findAllWorkItemsByTeamId(Long id) {
		return workItemRepository.findAllWorkItemsByTeamId(id);
	}

	public List<WorkItem> findByTeam(Team team) {
		return workItemRepository.findByTeam(team);
	}

	public List<WorkItem> findByUser(User user) {
		return workItemRepository.findByUser(user);
	}

	public List<WorkItem> findByDescriptionContaining(String description) {
		return workItemRepository.findByDescriptionContaining(description);
	}

	public List<WorkItem> findWorkItemsWithIssue() {
		return workItemRepository.findByIssueIdNotNull();
	}
}
