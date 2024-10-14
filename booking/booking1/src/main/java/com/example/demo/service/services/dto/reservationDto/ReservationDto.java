package com.example.demo.service.services.dto.reservationDto;

import java.sql.Date;

import com.example.demo.service.services.dto.propertyDto.PropertyForReservationDTO;
import com.example.demo.service.services.dto.userDto.UserForReservation;

public class ReservationDto {

	private int Id;
	private PropertyForReservationDTO property;	
	private UserForReservation user;
	private Date start_date;
	private Date end_date;
	
	public ReservationDto() {};
	
	public ReservationDto(int id, PropertyForReservationDTO property, UserForReservation user, Date start_date, Date end_date) {
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

	public PropertyForReservationDTO getProperty() {
		return property;
	}

	public void setProperty(PropertyForReservationDTO property) {
		this.property = property;
	}

	public UserForReservation getUser() {
		return user;
	}

	public void setUser(UserForReservation user) {
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
