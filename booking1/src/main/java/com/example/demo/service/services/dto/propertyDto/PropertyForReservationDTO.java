package com.example.demo.service.services.dto.propertyDto;

public class PropertyForReservationDTO {
	private int Id;
	private String title;
	private String description;
	private String type;
	private String location;
	private String picture_path;
	private double price;
	
	public PropertyForReservationDTO() {}

	public PropertyForReservationDTO(int id, String title, String description, String type, String location,
			String picture_path, double price) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.location = location;
		this.picture_path = picture_path;
		this.price = price;
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
}
