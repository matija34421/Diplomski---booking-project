package com.example.demo.service.services.dto.propertyDto;

import com.example.demo.service.services.dto.userDto.UserForPropertyDTO;

public class PropertyDTO {
	private int Id;
	private String title;
	private String description;
	private String type;
	private String location;
	private String address;
	private double price;
	private String picture_path;
	private UserForPropertyDTO landlord;
	
	public PropertyDTO() {}

	public PropertyDTO(int id, String title, String description, String type, String location,String address, double price,
			String picture_path, UserForPropertyDTO landlord) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.location = location;
		this.address = address;
		this.price = price;
		this.picture_path = picture_path;
		this.landlord = landlord;
	}
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture_path() {
		return picture_path;
	}

	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserForPropertyDTO getLandlord() {
		return landlord;
	}

	public void setLandlord(UserForPropertyDTO landlord) {
		this.landlord = landlord;
	}
	
	
}
