package com.example.demo.service.services.dto.propertyDto;

public class ReviewCreateDTO {

	private int propertyId;
	private int userId;
	private String review;
	private int star_number;
	
	public ReviewCreateDTO() {}
	
	public ReviewCreateDTO(int propertyId, int userId, String review, int star_number) {
		super();
		this.propertyId = propertyId;
		this.userId = userId;
		this.review = review;
		this.star_number = star_number;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
