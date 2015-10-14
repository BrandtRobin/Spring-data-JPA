package se.meer.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import se.meer.jpa.model.Token;

public interface TokenRepository extends CrudRepository<Token, Long>{

	Token findTokenByUsername(String username);
}
