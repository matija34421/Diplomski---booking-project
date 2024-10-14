package com.zavrsni.booking.domain.entities;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Title")
	private String Title;
	
	@Column(name = "Description")
	private String Description;
	
	@Column(name = "Type")
	private String Type;
	
	@ManyToOne
	@JoinColumn(name = "landlord_id", referencedColumnName = "Id")
	private User user;
	
	@OneToMany(mappedBy = "property")
	private List<Reservations> reservations;
	
	@OneToMany(mappedBy = "property")
	private List<Picture> pictures;
	
	public Property() {}
	
	public Property(int Id,String Title,String Description,String Type,int LandlordId,User user) {
		this.Id=Id;
		this.Title=Title;
		this.Description=Description;
		this.Type=Type;
		this.user=user;
		this.reservations = Collections.emptyList();
		this.pictures = Collections.emptyList();
	}
	
	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public String getTitle() {
		return this.Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	
	public String getDescription() {
		return this.Description;
	}
	public void setDescription(String desc) {
		this.Description = desc;
	}
	
	public String getType() {
		return this.Type;
	}
	public void setType(String type) {
		this.Type = type;
	}
	
	public User getLandlordID() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Reservations> getReservations(){
		return reservations;
	}
	public void setReservations(Reservations reservation) {
		this.reservations.add(reservation);
	}
	
	public List<Picture> getPictures(){
		return pictures;
	}
	public void setPictures(Picture picture) {
		this.pictures.add(picture);
	}

}