package se.meer.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.meer.jpa.model.Issue;
import se.meer.jpa.repository.IssueRepository;

public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public Issue addIssue(Issue issue) {
		issueRepository.save(issue);
		issueRepository.logIssue(issue);
		return issueRepository.save(issue);
	}

}
