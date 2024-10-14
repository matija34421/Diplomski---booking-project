package com.example.demo.domain.entities;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "property")
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String type;
	
	@Column
	private Double price;
	
	@Column
	private String location;
	
	@Column
	private String addres;
	@Column
	private String picture_path;
	@Column
	private boolean petFriendly;
	@Column
	private boolean wiFi;
	@Column
	private boolean kitchen;
	@Column
	private boolean breakfast;
	@Column
	private boolean lunch;
	@Column
	private boolean dinner;
	@Column
	private boolean AC;
	@Column
	private boolean smokingAllowed;
	@Column
	private boolean tv;
	@Column
	private boolean freeCancelation;
	@Column
	private int guestNumber;
	@Column 
	private String coordinateLat;
	@Column 
	private String coordinateLng;
	
	@ManyToOne
	@JoinColumn(name = "landlord_id", referencedColumnName = "Id")
	private User landlord;
	
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Picture> pictures;

	
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public void setPicture(Picture picture) {
		this.pictures.add(picture);
	}

	public Property() {}
	
	public Property(int id, String title, String description, String type, Double price, String location, String address,
			String picture_path, boolean petFriendly, boolean wiFi, boolean kitchen, boolean breakfast, boolean lunch,
			boolean dinner, boolean aC, boolean smokingAllowed, boolean tv,boolean freeCancelation, int guestNumber, String coordinateLat,
			String coordinateLng, User landlord, List<Reservation> reservations, List<Picture> pictures) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.price = price;
		this.location = location;
		this.addres = address;
		this.picture_path = picture_path;
		this.petFriendly = petFriendly;
		this.wiFi = wiFi;
		this.kitchen = kitchen;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		AC = aC;
		this.smokingAllowed = smokingAllowed;
		this.tv = tv;
		this.freeCancelation=freeCancelation;
		this.guestNumber = guestNumber;
		this.coordinateLat = coordinateLat;
		this.coordinateLng = coordinateLng;
		this.landlord = landlord;
		this.reservations = Collections.emptyList();
		this.pictures = Collections.emptyList();
	}

	public boolean isFreeCancelation() {
		return freeCancelation;
	}
	public void setFreeCancelation(boolean freeCancelation) {
		this.freeCancelation = freeCancelation;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getCoordinateLat() {
		return coordinateLat;
	}
	public void setCoordinateLat(String coordinateLat) {
		this.coordinateLat = coordinateLat;
	}
	public String getCoordinateLng() {
		return coordinateLng;
	}
	public void setCoordinateLng(String coordinateLng) {
		this.coordinateLng = coordinateLng;
	}
	public boolean isPetFriendly() {
		return petFriendly;
	}
	public void setPetFriendly(boolean petFriendly) {
		this.petFriendly = petFriendly;
	}
	public boolean isWiFi() {
		return wiFi;
	}
	public void setWiFi(boolean wiFi) {
		this.wiFi = wiFi;
	}
	public boolean isKitchen() {
		return kitchen;
	}
	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}
	public boolean isBreakfast() {
		return breakfast;
	}
	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}
	public boolean isLunch() {
		return lunch;
	}
	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}
	public boolean isDinner() {
		return dinner;
	}
	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}
	public boolean isAC() {
		return AC;
	}
	public void setAC(boolean aC) {
		AC = aC;
	}
	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}
	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}
	public boolean isTv() {
		return tv;
	}
	public void setTv(boolean tv) {
		this.tv = tv;
	}
	public int getGuestNumber() {
		return guestNumber;
	}
	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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

	public User getLandlord() {
		return landlord;
	}

	public void setLandlord(User landlord) {
		this.landlord = landlord;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public void setReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
	
}
