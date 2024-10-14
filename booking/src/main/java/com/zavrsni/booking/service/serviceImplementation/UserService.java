package com.zavrsni.booking.service.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zavrsni.booking.domain.entities.User;
import com.zavrsni.booking.persistance.repository.UserRepository;
import com.zavrsni.booking.service.serviceInterface.UserServiceInterface;

public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository repository;
	
	@Override
	public List<User> getUsers() {
		List<User> users = repository.getUsers();
		return users;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

}
