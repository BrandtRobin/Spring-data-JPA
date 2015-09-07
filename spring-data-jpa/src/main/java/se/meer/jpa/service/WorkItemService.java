package se.meer.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import se.meer.jpa.model.Team;
import se.meer.jpa.model.User;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.repository.WorkItemRepository;

public class WorkItemService {

	@Autowired
	private WorkItemRepository workItemRepository;

	public WorkItem createOrUpdateWorkItem(WorkItem workItem) {
		workItemRepository.save(workItem);
		return workItemRepository.save(workItem);
	}

	public Long deleteWorkItemById(Long id) {
		workItemRepository.delete(id);
		return id;
	}

	public List<WorkItem> findByStatus(String status) {
		return workItemRepository.findByStatus(status);
	}

	public List<WorkItem> findByTeam(Team team) {
		return workItemRepository.findByTeam(team);
	}

	public List<WorkItem> findByUser(User user) {
		return workItemRepository.findByUser(user);
	}

	public Slice<WorkItem> findByDescription(String searchDesc, Pageable pageable) {
		return workItemRepository.findByDescriptionLike(searchDesc, pageable);
	}

	public List<WorkItem> findWorkItemsWithIssue() {
		return workItemRepository.findByIssueIdNotNull();
	}
}
