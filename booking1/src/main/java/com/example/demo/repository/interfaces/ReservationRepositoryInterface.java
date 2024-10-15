package com.example.demo.repository.interfaces;

import java.util.List;

import com.example.demo.domain.entities.Reservation;

public interface ReservationRepositoryInterface {
	List<Reservation> getReservations();
	List<Reservation> getReservationsForUser(int id);
	Reservation getReservation(int id);
	Reservation createReservation(Reservation reservation);
	void deletReservation(int id);
	Reservation updateReservation(Reservation reservation);
	List<Reservation> getReservationsForProperty(int id);
}
