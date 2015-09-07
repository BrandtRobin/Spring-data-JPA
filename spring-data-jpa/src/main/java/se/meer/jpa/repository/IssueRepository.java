package se.meer.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long> {
}
