package com.example.demo.config;


public class AuthenticationResponse {
	private String token;
	private int Id;
	private String username;
	private String email;

	

	public AuthenticationResponse(String token, int id, String username, String email) {
		super();
		this.token = token;
		Id = id;
		this.username = username;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	
	
}
