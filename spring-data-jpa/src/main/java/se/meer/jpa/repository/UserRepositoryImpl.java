package se.meer.jpa.repository;

import se.meer.jpa.model.User;

public class UserRepositoryImpl implements UserCustomRepository {

	@Override
	public void logUser(User user) {
		System.out.println("0 # Logging employee: [" + user + "]");
	}
}
