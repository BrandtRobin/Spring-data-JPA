package se.meer.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Issue;
import se.meer.jpa.model.WorkItem;
import se.meer.jpa.repository.IssueRepository;
import se.meer.jpa.repository.WorkItemRepository;

public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public Issue addIssue(Issue issue) {
		issueRepository.save(issue);
		return issueRepository.save(issue);
	}
	
	public Issue updateIssue(Long id, Issue issueUpdate) {
		Issue issue = findIssueById(id);
		issue.setDescription(issueUpdate.getDescription());
		issue.setTitle(issueUpdate.getTitle());
		issue.setWorkItem(issueUpdate.getWorkItem());
		addIssue(issue);
		return issue;
	}	
	
	public Issue findIssueById(Long id) {
		Issue issue = issueRepository.findOne(id);
		return issue;
	}	
}
