package com.example.demo.service.services.dto.propertyDto;

import com.example.demo.service.services.dto.userDto.UserForPropertyDTO;

public class FullPropertyDTO {
	private int Id;
	private String title;
	private String description;
	private String type;
	private String location;
	private String addres;
	private double price;
	private String picture_path;
	private UserForPropertyDTO landlord;
	private boolean petFriendly;
	private boolean wiFi;
	private boolean kitchen;
	private boolean breakfast;
	private boolean lunch;
	private boolean dinner;
	private boolean AC;
	private boolean smokingAllowed;
	private boolean tv;
	private boolean freeCancelation;
	private int guestNumber;
	private String coordinateLat; 
	private String coordinateLng;
	
	public FullPropertyDTO() {
	}

	public FullPropertyDTO(int id, String title, String description, String type, String location,String addres, double price,
			String picture_path, UserForPropertyDTO landlord, boolean petFriendly, boolean wiFi, boolean kitchen,
			boolean breakfast, boolean lunch, boolean dinner, boolean aC, boolean smokingAllowed, boolean tv,boolean freeCancelation,
			int guestNumber, String coordinateLat, String coordinateLng) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.location = location;
		this.addres = addres;
		this.price = price;
		this.picture_path = picture_path;
		this.landlord = landlord;
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
	}
	
	

	public boolean isFreeCancelation() {
		return freeCancelation;
	}

	public void setFreeCancelation(boolean freeCancelation) {
		this.freeCancelation = freeCancelation;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
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

	public String getPicture_path() {
		return picture_path;
	}

	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

	public UserForPropertyDTO getLandlord() {
		return landlord;
	}

	public void setLandlord(UserForPropertyDTO landlord) {
		this.landlord = landlord;
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
	
	
}
