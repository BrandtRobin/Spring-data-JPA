package se.meer.jpa.repository;

import se.meer.jpa.model.Issue;

public class IssueRepositoryImpl implements IssueCustomRepository {

	@Override
	public void logIssue(Issue issue) {
		System.out.println("0 # Logging issue: [" + issue + "]");

	}
}
