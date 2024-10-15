package com.example.demo.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Property;
import com.example.demo.domain.entities.User;
import com.example.demo.repository.repositores.PropertyRepository;
import com.example.demo.repository.repositores.UserRepository;
import com.example.demo.service.exceptions.propertyExceptions.PropertyIllegalArgumentException;
import com.example.demo.service.exceptions.propertyExceptions.PropertyNotFoundException;

import com.example.demo.service.interfaces.PropertyServiceInterface;
import com.example.demo.service.services.dto.propertyDto.FullPropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyCreateDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyUpdateDTO;
import com.example.demo.service.services.dto.userDto.UserForPropertyDTO;

import jakarta.transaction.Transactional;


@Service
public class PropertyService implements PropertyServiceInterface {

	@Autowired
	PropertyRepository repository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public List<PropertyDTO> getProperties() {
		
		return repository.getProperties().stream().map(property-> new PropertyDTO(
		        property.getId(),
		        property.getTitle(),
		        property.getDescription(),
		        property.getType(),
		        property.getLocation(),
		        property.getAddres(),
		        property.getPrice(),
		        property.getPicture_path(),
		        conversionUserToUserForPropertyDTO(property.getLandlord())
		    )).collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public List<PropertyDTO> getPropertiesForUser(int id) {
		
		return repository.getPropertiesForUser(id).stream().map(property-> new PropertyDTO(
		        property.getId(),
		        property.getTitle(),
		        property.getDescription(),
		        property.getType(),
		        property.getLocation(),
		        property.getAddres(),
		        property.getPrice(),
		        property.getPicture_path(),
		        conversionUserToUserForPropertyDTO(property.getLandlord())
		    )).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public FullPropertyDTO getProperty(int id) {
		if(id<0) {
			throw new PropertyIllegalArgumentException(id);
		}
		
		Property property = repository.getProperty(id);
		
		if(property==null) {
			throw new PropertyNotFoundException(id);
		}
		
		FullPropertyDTO propertyDTO = new FullPropertyDTO(
		        property.getId(),
		        property.getTitle(),
		        property.getDescription(),
		        property.getType(),
		        property.getLocation(),
		        property.getAddres(),
		        property.getPrice(),
		        property.getPicture_path(),
		        conversionUserToUserForPropertyDTO(property.getLandlord()),
		        property.isPetFriendly(),
		        property.isWiFi(),
		        property.isKitchen(),
		        property.isBreakfast(),
		        property.isLunch(),
		        property.isDinner(),
		        property.isAC(),
		        property.isSmokingAllowed(),
		        property.isTv(),
		        property.isFreeCancelation(),
		        property.getGuestNumber(),
		        property.getCoordinateLat(),
		        property.getCoordinateLng()
		    );
		
		return propertyDTO;
	}

	@Transactional
	@Override
	public PropertyDTO createProperty(PropertyCreateDTO property) {
		if(property == null) {
			throw new PropertyIllegalArgumentException(property);
		}
		
		if(property.getType()==null
				&& property.getTitle()==null
				&& property.getDescription()==null
				&& property.getType()==null
				&& property.getLandlord()==0
				&& property.getGuestNumber()==0
				&& property.getCoordinateLat()==null
				&& property.getCoordinateLng()==null)
		{
			throw new PropertyIllegalArgumentException(property);
		}
		
		
		Property propertForCreating = new Property();
		propertForCreating.setId(0);
		propertForCreating.setDescription(property.getDescription());
		propertForCreating.setType(property.getType());
		propertForCreating.setTitle(property.getTitle());
		propertForCreating.setLocation(property.getLocation());
		propertForCreating.setAddres(property.getAddres());
		propertForCreating.setAC(property.isAC());
		propertForCreating.setBreakfast(property.isBreakfast());
		propertForCreating.setLunch(property.isLunch());
		propertForCreating.setDinner(property.isDinner());
		propertForCreating.setKitchen(property.isKitchen());
		propertForCreating.setCoordinateLat(property.getCoordinateLat());
		propertForCreating.setCoordinateLng(property.getCoordinateLng());
		propertForCreating.setPrice(property.getPrice());
		propertForCreating.setPicture_path(property.getPicture_path());
		propertForCreating.setLocation(property.getLocation());
		propertForCreating.setSmokingAllowed(property.isSmokingAllowed());
		propertForCreating.setTv(property.isTv());
		propertForCreating.setFreeCancelation(property.isFreeCancelation());
		propertForCreating.setPetFriendly(property.isPetFriendly());
		propertForCreating.setGuestNumber(property.getGuestNumber());
		
		User user = userRepository.getUser(property.getLandlord());
		
		propertForCreating.setLandlord(user);
		
		Property createdProperty = repository.createProperty(propertForCreating);
		PropertyDTO createdPropertyDTO = modelMapper.map(createdProperty, PropertyDTO.class);
		return createdPropertyDTO;
	}

	@Transactional
	@Override
	public void deleteProperty(int id) {
		repository.deleteProperty(id);
	}

	@Transactional
	@Override
	public FullPropertyDTO updateProperty(PropertyUpdateDTO property) {
	    if (property == null) {
	        throw new PropertyIllegalArgumentException(property);
	    }

	    Property propertyForUpdate = repository.getProperty(property.getId());

	    if (property.getDescription() != null) {
	        propertyForUpdate.setDescription(property.getDescription());
	    }
	    if (property.getType() != null) {
	        propertyForUpdate.setType(property.getType());
	    }
	    if (property.getTitle() != null) {
	        propertyForUpdate.setTitle(property.getTitle());
	    }
	    if (property.getLocation() != null) {
	        propertyForUpdate.setLocation(property.getLocation());
	    }
	    if(property.getAddres() != null) {
	    	propertyForUpdate.setAddres(property.getAddres());
	    }
	    if (property.getPrice() > 0) {
	        propertyForUpdate.setPrice(property.getPrice());
	    }
	    if (property.getPicture_path() != null) {
	        propertyForUpdate.setPicture_path(property.getPicture_path());
	    }
	    if (property.isPetFriendly() != propertyForUpdate.isPetFriendly()) {
	        propertyForUpdate.setPetFriendly(property.isPetFriendly());
	    }
	    if (property.isWiFi() != propertyForUpdate.isWiFi()) {
	        propertyForUpdate.setWiFi(property.isWiFi());
	    }
	    if (property.isKitchen() != propertyForUpdate.isKitchen()) {
	        propertyForUpdate.setKitchen(property.isKitchen());
	    }
	    if (property.isBreakfast() != propertyForUpdate.isBreakfast()) {
	        propertyForUpdate.setBreakfast(property.isBreakfast());
	    }
	    if (property.isLunch() != propertyForUpdate.isLunch()) {
	        propertyForUpdate.setLunch(property.isLunch());
	    }
	    if (property.isDinner() != propertyForUpdate.isDinner()) {
	        propertyForUpdate.setDinner(property.isDinner());
	    }
	    if (property.isAC() != propertyForUpdate.isAC()) {
	        propertyForUpdate.setAC(property.isAC());
	    }
	    if (property.isSmokingAllowed() != propertyForUpdate.isSmokingAllowed()) {
	        propertyForUpdate.setSmokingAllowed(property.isSmokingAllowed());
	    }
	    if (property.isTv() != propertyForUpdate.isTv()) {
	        propertyForUpdate.setTv(property.isTv());
	    }
	    if (property.getGuestNumber() > 0) {
	        propertyForUpdate.setGuestNumber(property.getGuestNumber());
	    }
	    if (property.getCoordinateLat() != null) {
	        propertyForUpdate.setCoordinateLat(property.getCoordinateLat());
	    }
	    if (property.getCoordinateLng() != null) {
	        propertyForUpdate.setCoordinateLng(property.getCoordinateLng());
	    }
	    if (property.isFreeCancelation() != propertyForUpdate.isFreeCancelation()) {
	        propertyForUpdate.setFreeCancelation(property.isFreeCancelation());
	    }

	    Property updatedProperty = repository.updateProperty(propertyForUpdate);

	    FullPropertyDTO propertyDTO = new FullPropertyDTO(
	        updatedProperty.getId(),
	        updatedProperty.getTitle(),
	        updatedProperty.getDescription(),
	        updatedProperty.getType(),
	        updatedProperty.getLocation(),
	        updatedProperty.getAddres(),
	        updatedProperty.getPrice(),
	        updatedProperty.getPicture_path(),
	        conversionUserToUserForPropertyDTO(updatedProperty.getLandlord()),
	        updatedProperty.isPetFriendly(),
	        updatedProperty.isWiFi(),
	        updatedProperty.isKitchen(),
	        updatedProperty.isBreakfast(),
	        updatedProperty.isLunch(),
	        updatedProperty.isDinner(),
	        updatedProperty.isAC(),
	        updatedProperty.isSmokingAllowed(),
	        updatedProperty.isTv(),
	        updatedProperty.isFreeCancelation(),
	        updatedProperty.getGuestNumber(),
	        updatedProperty.getCoordinateLat(),
	        updatedProperty.getCoordinateLng()
	    );

	    return propertyDTO;
	}


	public UserForPropertyDTO conversionUserToUserForPropertyDTO(User user) {
		UserForPropertyDTO dto = new UserForPropertyDTO(user.getId(),user.getUsername(),user.getEmail(),user.getPhone());
		return dto;
	}
}
