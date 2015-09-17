package se.meer.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import se.meer.jpa.model.Issue;
import se.meer.jpa.repository.IssueRepository;

public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public Issue createOrUpdateIssue(Issue issue) {
		return issueRepository.save(issue);
	}

	public Issue findIssueById(Long id) {
		Issue issue = issueRepository.findOne(id);
		return issue;
	}
	
	public Page<Issue> findAll(Pageable pageable) {
		return issueRepository.findAll(pageable);
	}

}
