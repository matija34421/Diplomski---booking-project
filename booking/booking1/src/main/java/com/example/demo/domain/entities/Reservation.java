package com.example.demo.domain.entities;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;
	
	@ManyToOne
	@JoinColumn(name = "property_id",referencedColumnName = "Id")
	private Property property;	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "Id")
	private User user;
	@Column
	private Date start_date;
	@Column
	private Date end_date;
	


	public Reservation() {}
		
	public Reservation(int id, Property property, User user, Date start_date, Date end_date) {
		super();
		Id = id;
		this.property = property;
		this.user = user;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}
