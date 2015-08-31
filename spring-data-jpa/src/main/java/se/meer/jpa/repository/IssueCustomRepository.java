package se.meer.jpa.repository;

import se.meer.jpa.model.Issue;

public interface IssueCustomRepository {
	void logIssue(Issue issue);
}
