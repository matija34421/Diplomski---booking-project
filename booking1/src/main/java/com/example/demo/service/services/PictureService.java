package com.example.demo.service.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.entities.Picture;
import com.example.demo.domain.entities.Property;
import com.example.demo.repository.repositores.PictureRepository;
import com.example.demo.repository.repositores.PropertyRepository;
import com.example.demo.service.exceptions.pictureExceptions.PictureIllegalArgumentException;
import com.example.demo.service.exceptions.propertyExceptions.PropertyIllegalArgumentException;
import com.example.demo.service.interfaces.PictureServiceInterface;
import com.example.demo.service.services.dto.pictureDto.PictureCreateDTO;
import com.example.demo.service.services.dto.pictureDto.PictureDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyForPictureDTO;
import jakarta.transaction.Transactional;

@Service
public class PictureService implements PictureServiceInterface{

	@Autowired
	PictureRepository repository;
	@Autowired
	PropertyRepository propertyRepository;
	
	@Transactional
	@Override
	public List<PictureDTO> getPicturesForProperty(int id) {
		if(id<=0) {
			throw new PropertyIllegalArgumentException(id);
		}
		
		Property property = propertyRepository.getProperty(id);

		List<PictureDTO> pictures = repository.getPicturesForProperty(id).stream().map(picture -> new PictureDTO(picture.getId(), conversionPropertyToPropertyForPictureDTO(property), picture.getPicturePath())).collect(Collectors.toList());
		return pictures;
	}

	@Transactional
	@Override
	public void createPicture(PictureCreateDTO picture) {
		if(picture == null) {
			throw new PictureIllegalArgumentException(picture);
		}
		
		if(picture.getProperty_id()<=0&& picture.getPicturePath()==null)
		{
			throw new PictureIllegalArgumentException(picture);
		}
		
		
		Picture pictureForCreating = new Picture();
		pictureForCreating.setId(0);
		pictureForCreating.setPicturePath(picture.getPicturePath());
		
		Property property = propertyRepository.getProperty(picture.getProperty_id());
		
		pictureForCreating.setProperty(property);
		
		repository.createPicture(pictureForCreating);
		
	}

	@Transactional
	@Override
	public void deletePicture(int id) {
		repository.deletePicture(id);
	}

	public PropertyForPictureDTO conversionPropertyToPropertyForPictureDTO(Property property) {
		PropertyForPictureDTO dto = new PropertyForPictureDTO(property.getId(),property.getTitle(),property.getDescription(),property.getType(),property.getLocation(),property.getPrice());
		return dto;
	}
}
