package com.example.demo.repository.repositores;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entities.User;
import com.example.demo.repository.interfaces.UserRepositoryInterface;
import com.example.demo.service.exceptions.userExceptions.UserIllegalArgumentException;
import com.example.demo.service.exceptions.userExceptions.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository implements UserRepositoryInterface {

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public List<User> getUsers() {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User", User.class);
		List<User> list = query.getResultList(); 
		return list;
	}

	@Transactional
	@Override
	public User getUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User where id=:id", User.class);
		query.setParameter("id", id);
		try {
			User user = query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			throw new UserNotFoundException(id);
		}
	}

	@Transactional
	@Override
	public User createUser(User user) {
		try {
			Session session = entityManager.unwrap(Session.class);
			session.persist(user);
			return user;
		} catch (PersistenceException e) {
			throw new UserIllegalArgumentException(user.getEmail());
		}
	}

	@Transactional
	@Override
	public void deleteUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		User user = getUser(id);
		session.remove(user);
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		Session session = entityManager.unwrap(Session.class);
        session.merge(user);
        User updatedUser = getUser(user.getId());
        return updatedUser;
	}

	@Override
	public boolean findUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	@Override
	public boolean findUserByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User where email=:email", User.class);
		query.setParameter("email", email);
		try {
			User user = query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			throw new UserNotFoundException(email);
		}
	}

	@Transactional
	@Override
	public User findByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User where email=:email", User.class);
		query.setParameter("email", email);
		try {
			User user = query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			throw new UserNotFoundException(email);
		}
	}

}
