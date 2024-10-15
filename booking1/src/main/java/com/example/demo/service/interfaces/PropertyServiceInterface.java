package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.service.services.dto.propertyDto.FullPropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyCreateDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyUpdateDTO;

public interface PropertyServiceInterface {
	List<PropertyDTO> getProperties();
	List<PropertyDTO> getPropertiesForUser(int id);
	FullPropertyDTO getProperty(int id);
	PropertyDTO createProperty(PropertyCreateDTO property);
	void deleteProperty(int id);
	FullPropertyDTO updateProperty(PropertyUpdateDTO property);
}
