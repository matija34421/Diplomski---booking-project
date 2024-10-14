package com.zavrsni.booking.persistance.repositoryInterface;

import java.util.List;

import com.zavrsni.booking.domain.entities.User;

public interface UserRepositoryInterface {
	List<User> getUsers();
	User getUser(int id);
	void createUser(User user);
	void updateUser(User user);
	void deleteUser(int id);
}
