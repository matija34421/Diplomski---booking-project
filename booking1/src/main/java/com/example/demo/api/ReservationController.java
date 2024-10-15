package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.exceptions.reservationExceptions.ReservationNotFoundException;
import com.example.demo.service.services.ReservationService;
import com.example.demo.service.services.dto.reservationDto.ReservationCreateDTO;
import com.example.demo.service.services.dto.reservationDto.ReservationDto;
import com.example.demo.service.services.dto.reservationDto.ReservationUpdateDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservationController {
	@Autowired
	private ReservationService service;
	
	@GetMapping("/reservations")
	public List<ReservationDto> getReservations(){
		return service.getReservations();
	}
	
	@GetMapping("/reservations/userId/{id}")
	public List<ReservationDto> getReservationsForUser(@PathVariable int id){
		return service.getReservationsForUser(id);
	}
	
	@GetMapping("/reservations/propertyId/{id}")
	public List<ReservationDto> getReservationsForProperty(@PathVariable int id){
		return service.getReservationsForProperty(id);
	}
	
	@GetMapping("/reservations/{id}")
	public ResponseEntity<ReservationDto> getReservation(@PathVariable int id){
		ReservationDto reservationDto = service.getReservation(id);
		return ResponseEntity.ok(reservationDto);
	}
	
	@PostMapping("/reservations")
	public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationCreateDTO reservation) {
		ReservationDto createdReservation = service.createReservation(reservation);
		return ResponseEntity.ok(createdReservation);
	}
	
	@DeleteMapping("/reservations/{id}")
	public ResponseEntity<ReservationDto> deleteReservation(@PathVariable int id) {
		try {
	        service.deleteReservation(id);
	        return ResponseEntity.noContent().build();
	    } catch (ReservationNotFoundException ex) {
	        throw ex;
	    }
	}
	
	@PutMapping("/reservations")
	public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationUpdateDTO reservation) {
		ReservationDto reservationDto = service.updateReservation(reservation);
		return ResponseEntity.ok(reservationDto);
	}
}
