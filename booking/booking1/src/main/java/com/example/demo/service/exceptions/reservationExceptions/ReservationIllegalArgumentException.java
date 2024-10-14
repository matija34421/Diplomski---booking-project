package com.example.demo.service.exceptions.reservationExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.demo.service.services.dto.reservationDto.ReservationCreateDTO;
import com.example.demo.service.services.dto.reservationDto.ReservationDto;
import com.example.demo.service.services.dto.reservationDto.ReservationUpdateDTO;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Rezervacija nije pronadjena")
public class ReservationIllegalArgumentException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ReservationIllegalArgumentException(int propertyId) {
		super("ID rezervacije mora biti veci od 0");
	}

	public ReservationIllegalArgumentException(ReservationDto reservation) {
		super("Lose prosledjena rezervacija!");
	}

	public ReservationIllegalArgumentException(ReservationCreateDTO reservation) {
		super("Lose prosledjena rezervacija!");
	}

	public ReservationIllegalArgumentException(ReservationUpdateDTO reservation) {
		super("Lose prosledjena rezervacija!");
	}
}
