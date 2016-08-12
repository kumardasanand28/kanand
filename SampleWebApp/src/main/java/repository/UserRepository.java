package repository;

import java.util.List;

import model.User;

public interface UserRepository {

	User save(User user);
	void remove (User user);
	List<User> findAll();
	User findUser(String email);
}
