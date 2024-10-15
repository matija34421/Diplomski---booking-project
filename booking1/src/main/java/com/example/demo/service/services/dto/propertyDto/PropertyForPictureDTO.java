package com.example.demo.service.services.dto.propertyDto;

public class PropertyForPictureDTO {
	private int Id;
	private String title;
	private String description;
	private String type;
	private String location;
	private double price;
	
	public PropertyForPictureDTO() {}
	

	
	public PropertyForPictureDTO(int id, String title, String description, String type, String location, double price) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.location = location;
		this.price = price;
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
