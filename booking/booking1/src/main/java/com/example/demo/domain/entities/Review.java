package com.example.demo.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;
	
	@ManyToOne
	@JoinColumn(name = "property_id",referencedColumnName = "Id")
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "Id")
	private User user;
	
	@Column
	private String review;
	
	@Column int star_number;
	
	public Review() {}

	public Review(int id, Property property, User user, String review, int star_number) {
		super();
		Id = id;
		this.property = property;
		this.user = user;
		this.review = review;
		this.star_number = star_number;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
