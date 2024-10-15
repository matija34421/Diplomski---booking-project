package com.example.demo.repository.interfaces;

import java.util.List;

import com.example.demo.domain.entities.Property;

public interface PropertyRepositoryInterface {
	List<Property> getProperties();
	List<Property> getPropertiesForUser(int id);
	Property getProperty(int id);
	Property createProperty(Property property);
	void deleteProperty(int id);
	Property updateProperty(Property property);
}
