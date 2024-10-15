package com.example.demo.service.interfaces;

import java.util.List;
import com.example.demo.service.services.dto.reservationDto.ReservationCreateDTO;
import com.example.demo.service.services.dto.reservationDto.ReservationDto;
import com.example.demo.service.services.dto.reservationDto.ReservationUpdateDTO;

public interface ReservationServiceInterface {
	List<ReservationDto> getReservations();
	List<ReservationDto> getReservationsForUser(int id);
	List<ReservationDto> getReservationsForProperty(int id);
	ReservationDto getReservation(int id);
	ReservationDto createReservation(ReservationCreateDTO reservation);
	void deleteReservation(int id);
	ReservationDto updateReservation(ReservationUpdateDTO reservation);
}
