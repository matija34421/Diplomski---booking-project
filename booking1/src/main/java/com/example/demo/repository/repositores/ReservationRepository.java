package com.example.demo.repository.repositores;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entities.Reservation;
import com.example.demo.repository.interfaces.ReservationRepositoryInterface;
import com.example.demo.service.exceptions.reservationExceptions.ReservationNotFoundException;

import jakarta.persistence.EntityManager;

@Repository
public class ReservationRepository  implements ReservationRepositoryInterface{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Reservation> getReservations() {
		Session session = entityManager.unwrap(Session.class);
		Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
		List<Reservation> list = query.getResultList();
		return list;
	}
	
	@Override
	public Reservation getReservation(int id) {
		Session session = entityManager.unwrap(Session.class);
		Reservation reservation = session.get(Reservation.class, id);
		
		if(reservation==null) {
			throw new ReservationNotFoundException(id);
		}
		
		return reservation;
	}

	@Override
	public Reservation createReservation(Reservation reservation) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(reservation);
		return reservation;
	}

	@Override
	public void deletReservation(int id) {
		Session session = entityManager.unwrap(Session.class);
		Reservation reservation = getReservation(id);
		session.remove(reservation);
	}

	@Override
	public Reservation updateReservation(Reservation reservation) {
		Session session = entityManager.unwrap(Session.class);
		session.merge(reservation);
		return reservation;
	}

	@Override
	public List<Reservation> getReservationsForProperty(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Reservation> query = session.createQuery("from Reservation where property.Id=:id", Reservation.class);
		query.setParameter("id", id);
		List<Reservation> list = query.getResultList();
		return list;
	}

	@Override
	public List<Reservation> getReservationsForUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Reservation> query = session.createQuery("from Reservation where user.Id=:id", Reservation.class);
		query.setParameter("id", id);
		List<Reservation> list = query.getResultList();
		return list;
	}


}
