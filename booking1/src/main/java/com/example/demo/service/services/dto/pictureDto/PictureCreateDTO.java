package com.example.demo.service.services.dto.pictureDto;

public class PictureCreateDTO {
	private int property_id;
	private String picturePath;
	
	public PictureCreateDTO() {}
	
	public PictureCreateDTO(int property_id, String picturePath) {
		super();
		this.property_id = property_id;
		this.picturePath = picturePath;
	}
	
	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
}
