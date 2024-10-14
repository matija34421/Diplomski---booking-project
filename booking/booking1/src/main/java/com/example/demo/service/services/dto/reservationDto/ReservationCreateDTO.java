package com.example.demo.service.services.dto.reservationDto;

import java.sql.Date;

public class ReservationCreateDTO {
	private int propertyId;	
	private int userId;
	private Date start_date;
	private Date end_date;
	
	public ReservationCreateDTO() {}
	
	public ReservationCreateDTO(int propertyId, int userId, Date start_date, Date end_date) {
		super();
		this.propertyId = propertyId;
		this.userId = userId;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
