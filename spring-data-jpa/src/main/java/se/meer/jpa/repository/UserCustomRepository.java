package se.meer.jpa.repository;

import se.meer.jpa.model.User;

public interface UserCustomRepository {
	void logUser(User user);
}
