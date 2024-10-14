package com.example.demo.repository.repositores;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Property;
import com.example.demo.repository.interfaces.PropertyRepositoryInterface;
import com.example.demo.service.exceptions.propertyExceptions.PropertyNotFoundException;

import jakarta.persistence.EntityManager;

@Repository
public class PropertyRepository implements PropertyRepositoryInterface {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Property> getProperties() {
		Session session = entityManager.unwrap(Session.class);
		Query<Property> query = session.createQuery("from Property", Property.class);
		List<Property> list = query.getResultList();
		return list;
	}

	@Override
	public Property getProperty(int id) {
		Session session = entityManager.unwrap(Session.class);
		Property property = session.get(Property.class, id);
		
		if(property==null) {
			throw new PropertyNotFoundException(id);
		}
		
		return property;
	}

	@Override
	public Property createProperty(Property property) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(property);
		return property;
	}

	@Override
	public void deleteProperty(int id) {
		Session session = entityManager.unwrap(Session.class);
		Property property = getProperty(id);
		session.remove(property);
	}

	@Override
	public Property updateProperty(Property property) {
		Session session = entityManager.unwrap(Session.class);
		session.merge(property);
		return property;
	}

	@Override
	public List<Property> getPropertiesForUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Property> query = session.createQuery("from Property where landlord.Id=:id", Property.class);
		query.setParameter("id", id);
		List<Property> list = query.getResultList();
		return list;
	}

}
