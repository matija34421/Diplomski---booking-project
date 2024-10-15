package com.example.demo.service.services.dto.userDto;

import com.example.demo.domain.entities.Role;

public class UserUpdateDTO {
	private int Id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phone;
	private Role role;
	
	public UserUpdateDTO() {}
	
	public UserUpdateDTO(int Id,String username, String password, String email, String address, String phone,Role role) {
		super();
		this.Id=Id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.role = role;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}