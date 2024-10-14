package com.zavrsni.booking.domain.entities;

import jakarta.persistence.*;

@Entity
public class Picture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne
	@JoinColumn(name = "property_id",referencedColumnName = "Id")
	private Property property;
	
	@Column(name = "picture_path")
	private String picturePath;
	
	public Picture() {}
	
	public Picture(int Id,Property property,String picturePath) {
		this.Id = Id;
		this.property=property;
		this.picturePath=picturePath;
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

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
} 
