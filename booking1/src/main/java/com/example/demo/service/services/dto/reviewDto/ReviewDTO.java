package com.example.demo.service.services.dto.reviewDto;

public class ReviewDTO {
	private int Id;

	private int propertyId;

	private String userEmail;

	private String review;
	
	private int star_number;
	
	public ReviewDTO() {}

	public ReviewDTO(int id, int propertyId, String userEmail, String review, int star_number) {
		super();
		Id = id;
		this.propertyId = propertyId;
		this.userEmail = userEmail;
		this.review = review;
		this.star_number = star_number;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getProperty() {
		return propertyId;
	}

	public void setProperty(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getUser() {
		return userEmail;
	}

	public void setUser(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getStar_number() {
		return star_number;
	}

	public void setStar_number(int star_number) {
		this.star_number = star_number;
	}
	
	
}
