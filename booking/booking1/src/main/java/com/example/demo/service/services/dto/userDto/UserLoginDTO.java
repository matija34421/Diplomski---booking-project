package com.example.demo.service.services.dto.userDto;

public class UserLoginDTO {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public UserLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
