package com.zavrsni.booking.domain.entities;

import java.util.Collections;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "username")
	private String Username;
	
	@Column(name = "passowrd")
	private String Password;
	
	@Column(name = "email")
	private String Email;
	
	@Column(name = "address")
	private String Address;
	
	@Column(name = "phone")
	private String Phone;
	
	@Column(name = "role")
	private String Role;
	
	@OneToMany(mappedBy = "user")
	private List<Property> properties;
	
	@OneToMany(mappedBy = "user")
	private List<Reservations> reservations;
	
	@ManyToMany(mappedBy = "users")
	private List<Conversation> conversations;
	
	public User() {}
	
	public User(int Id,String Username,String Password,String Email,String Address,String Phone,String Role) {
		this.Id=Id;
		this.Username=Username;
		this.Password=Password;
		this.Email=Email;
		this.Address=Address;
		this.Phone=Phone;
		this.Role=Role;
		this.reservations=Collections.emptyList();
		this.properties=Collections.emptyList();
		this.conversations=Collections.emptyList();
	}
	
	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	public String getUsername() {
		return this.Username;
	}
	public void setUsername(String username) {
		this.Username=username;
	}
	
	public String getPassword() {
		return this.Password;
	}
	public void getPassword(String password) {
		this.Password=password;
	}
	
	public String getEmail() {
		return this.Email;
	}
	public void setEmail(String email) {
		this.Email=email;
	}
	
	public String getAddress() {
		return this.Address;
	}
	public void setAddress(String address) {
		this.Address=address;
	}
	
	public String getPhone() {
		return this.Phone;
	}
	public void setPhone(String phone) {
		this.Phone=phone;
	}
	
	public String getRole() {
		return this.Role;
	}
	public void setRole(String role) {
		this.Role=role;
	}
	
	public List<Property> getProperties(){
		return properties;
	}
	public void setProperties(Property property) {
		this.properties.add(property);
	}
	
	public List<Reservations> getReservations(){
		return reservations;
	}
	
	public void setReservations(Reservations reservation) {
		this.reservations.add(reservation);
	}
	
	public List<Conversation> getConversations(){
		return conversations;
	}
	
	public void setConversation(Conversation conversation) {
		this.conversations.add(conversation);
	}

}