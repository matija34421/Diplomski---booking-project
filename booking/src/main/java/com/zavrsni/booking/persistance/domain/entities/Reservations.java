package com.zavrsni.booking.domain.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Reservations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne
	@JoinColumn(name = "property_id", referencedColumnName = "Id")
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "Id")
	private User user;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	public Reservations() {}
	
	public Reservations(int Id,Property property,User user,Date startDate,Date endDate) {
		this.Id = Id;
		this.property = property;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id=id;
	}
	
	public Property getProperty() {
		return this.property;
	}
	public void setProperty(Property property) {
		this.property=property;
	}
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user=user;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date date) {
		this.startDate=date;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date date) {
		this.endDate=date;
	}
}
