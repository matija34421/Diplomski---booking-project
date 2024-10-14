package com.example.demo.service.services.dto.pictureDto;

import com.example.demo.service.services.dto.propertyDto.PropertyForPictureDTO;

public class PictureDTO {
	private int Id;
	private PropertyForPictureDTO property;
	private String picturePath;
	
	public PictureDTO() {}
	
	public PictureDTO(int id, PropertyForPictureDTO property, String picturePath) {
		super();
		Id = id;
		this.property = property;
		this.picturePath = picturePath;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public PropertyForPictureDTO getProperty() {
		return property;
	}

	public void setProperty(PropertyForPictureDTO property) {
		this.property = property;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
}
