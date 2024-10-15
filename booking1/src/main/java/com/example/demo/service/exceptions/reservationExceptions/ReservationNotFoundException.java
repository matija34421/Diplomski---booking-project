package com.example.demo.service.exceptions.reservationExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rezervacija nije pronadjen")
public class ReservationNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ReservationNotFoundException(int reservationId) {
		super("Rezervacija sa id-om: "+reservationId+" nije pronadjena");
	}
}
