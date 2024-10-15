package com.example.demo.service.services.dto.reservationDto;

import java.sql.Date;

public class ReservationUpdateDTO {
	private int Id;
	private Date start_date;
	private Date end_date;
	
	public ReservationUpdateDTO() {}
	
	public ReservationUpdateDTO(int id, Date start_date, Date end_date) {
		super();
		this.Id=id;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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
