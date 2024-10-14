package com.example.demo.service.services.dto.userDto;

public class UserForReservation {
	private int Id;
	private String username;
	private String email;
	private String phone;
	
	public UserForReservation() {}
	
	public UserForReservation(int id, String username, String email, String phone) {
		super();
		this.Id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
