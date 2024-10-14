package com.example.demo.repository.interfaces;

import java.util.List;
import com.example.demo.domain.entities.User;

public interface UserRepositoryInterface{
	List<User> getUsers();
	User getUser(int id);
	User createUser(User user);
	void deleteUser(int id);
	User updateUser(User user);
	boolean findUserByEmail(String email);
	User findByEmail(String email);
	boolean findUserByEmailAndPassword(String email,String password);
}
