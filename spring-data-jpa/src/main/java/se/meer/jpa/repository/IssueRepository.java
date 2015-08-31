package se.meer.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long>, IssueCustomRepository {

	List<Issue>findByIssueNumber(Long issueNumber);
}
