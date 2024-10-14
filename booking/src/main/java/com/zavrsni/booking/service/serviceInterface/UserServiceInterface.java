package com.zavrsni.booking.service.serviceInterface;

import java.util.List;

import com.zavrsni.booking.domain.entities.User;

public interface UserServiceInterface {
	List<User> getUsers();
	User getUser(int id);
	void createUser(User user);
	void updateUser(User user);
	void deleteUser(int id);
}
