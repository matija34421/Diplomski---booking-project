package com.zavrsni.booking.persistance.repository;
import java.util.List;
import java.sql.*;
import com.zavrsni.booking.domain.entities.User;
import com.zavrsni.booking.persistance.repositoryInterface.UserRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.zavrsni.booking.persistance.dbconnection.*;

public class UserRepository implements UserRepositoryInterface {
	
	Connection connection = DBConnection.Conn();
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getUsers() {
		String jqpl = "select u from users u";
		TypedQuery<User> query = entityManager.createQuery(jqpl, User.class);
		return query.getResultList();
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
