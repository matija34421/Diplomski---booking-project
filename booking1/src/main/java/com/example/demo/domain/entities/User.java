package com.example.demo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;
	@Column
	private String username;
	@Column
	private String password;
	@Column(unique = true)
	private String email;
	@Column
	private String address;
	@Column
	private String phone;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Property> properties;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;
	

	
	public User() { }
	
	public User(int id, String username, String password, String email, String address, String phone, Role role) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.properties = Collections.emptyList();
		this.reservations = Collections.emptyList();

	}
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public void setProperty(Property property) {
		this.properties.add(property);
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
	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", role=" + role + "]";
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return role.getAuthorities();
	  }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
