package se.meer.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
