package se.meer.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long> {
	
	Page<Issue> findAll(Pageable pageable);
}
