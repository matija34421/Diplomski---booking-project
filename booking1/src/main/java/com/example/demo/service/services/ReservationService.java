package com.example.demo.service.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.entities.Property;
import com.example.demo.domain.entities.Reservation;
import com.example.demo.domain.entities.User;
import com.example.demo.repository.repositores.PropertyRepository;
import com.example.demo.repository.repositores.ReservationRepository;
import com.example.demo.repository.repositores.UserRepository;
import com.example.demo.service.exceptions.reservationExceptions.ReservationIllegalArgumentException;
import com.example.demo.service.exceptions.reservationExceptions.ReservationNotFoundException;
import com.example.demo.service.interfaces.ReservationServiceInterface;
import com.example.demo.service.services.dto.propertyDto.PropertyForReservationDTO;
import com.example.demo.service.services.dto.reservationDto.ReservationCreateDTO;
import com.example.demo.service.services.dto.reservationDto.ReservationDto;
import com.example.demo.service.services.dto.reservationDto.ReservationUpdateDTO;
import com.example.demo.service.services.dto.userDto.UserForReservation;

import jakarta.transaction.Transactional;

@Service
public class ReservationService implements ReservationServiceInterface {

	@Autowired
	ReservationRepository repository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public List<ReservationDto> getReservations() {
		return repository.getReservations().stream().map(reservation-> new ReservationDto(reservation.getId(),conversionPropertyToReservationDTO(reservation.getProperty()),conversionUserToUserForReservationDTO(reservation.getUser()),reservation.getStart_date(),reservation.getEnd_date())).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public ReservationDto getReservation(int id) {
		if(id<0) {
			throw new ReservationIllegalArgumentException(id);
		}
		
		Reservation reservation = repository.getReservation(id);
		
		if(reservation==null) {
			throw new ReservationNotFoundException(id);
		}
		
		ReservationDto reservationDto = new ReservationDto(reservation.getId(),conversionPropertyToReservationDTO(reservation.getProperty()),conversionUserToUserForReservationDTO(reservation.getUser()),reservation.getStart_date(),reservation.getEnd_date());
		
		return reservationDto;
	}

	@Transactional
	@Override
	public ReservationDto createReservation(ReservationCreateDTO reservation) {
		if(reservation == null) {
			throw new ReservationIllegalArgumentException(reservation);
		}
		
		if(reservation.getUserId()<0
				&& reservation.getPropertyId()<0
				&& reservation.getStart_date()==null
				&& reservation.getEnd_date() == null)
 		{
			throw new ReservationIllegalArgumentException(reservation);
		}
		
		List<Reservation> datesToCheck = repository.getReservationsForProperty(reservation.getPropertyId());
		
		LocalDate startDateToCheck = reservation.getStart_date().toLocalDate();
		LocalDate endDateToCheck = reservation.getEnd_date().toLocalDate();

		for (Reservation r : datesToCheck) {
		    LocalDate existingStartDate = r.getStart_date().toLocalDate();
		    LocalDate existingEndDate = r.getEnd_date().toLocalDate();

		    if ((startDateToCheck.isBefore(existingEndDate) && endDateToCheck.isAfter(existingStartDate)) ||
		            (startDateToCheck.equals(existingStartDate) || endDateToCheck.equals(existingEndDate))) {
		        throw new ReservationIllegalArgumentException(reservation.getPropertyId());
		    }
		}
		
		
		Reservation reservationForCreating = new Reservation();
		reservationForCreating.setId(0);
		reservationForCreating.setStart_date(reservation.getStart_date());
		reservationForCreating.setEnd_date(reservation.getEnd_date());
		
		User user = userRepository.getUser(reservation.getUserId());
		Property property = propertyRepository.getProperty(reservation.getPropertyId());
		
		reservationForCreating.setUser(user);
		reservationForCreating.setProperty(property);
		
		Reservation createdReservation = repository.createReservation(reservationForCreating);
		ReservationDto reservationDto = modelMapper.map(createdReservation, ReservationDto.class);
		return reservationDto;
	}

	@Transactional
	@Override
	public void deleteReservation(int id) {
		repository.deletReservation(id);
	}

	@Transactional
	@Override
	public ReservationDto updateReservation(ReservationUpdateDTO reservation) {
		if(reservation == null) {
			throw new ReservationIllegalArgumentException(reservation);
		}
		
		Reservation reservationForUpdate = repository.getReservation(reservation.getId());
		
		if(reservation.getStart_date()!=null) {
			reservationForUpdate.setStart_date(reservation.getStart_date());
		}
		if(reservation.getEnd_date()!=null) {
			reservationForUpdate.setEnd_date(reservation.getEnd_date());
		}
		
		Reservation reservationForUpdate2 = repository.getReservation(reservation.getId());
		Reservation updatedReservation = repository.updateReservation(reservationForUpdate2);
		ReservationDto reservationDto = new ReservationDto(updatedReservation.getId(),conversionPropertyToReservationDTO(updatedReservation.getProperty()),conversionUserToUserForReservationDTO(updatedReservation.getUser()),updatedReservation.getStart_date(),updatedReservation.getEnd_date());
		return reservationDto;
	}
	
	public UserForReservation conversionUserToUserForReservationDTO(User user) {
		UserForReservation dto = new UserForReservation(user.getId(),user.getUsername(),user.getEmail(),user.getPhone());
		return dto;
	}
	
	public PropertyForReservationDTO conversionPropertyToReservationDTO(Property property) {
		PropertyForReservationDTO dto = new PropertyForReservationDTO(property.getId(),property.getTitle(),property.getDescription(),property.getType(),property.getLocation(),property.getPicture_path(),property.getPrice());
		return dto;
	}

	@Override
	public List<ReservationDto> getReservationsForUser(int id) {
		return repository.getReservationsForUser(id).stream().map(reservation-> new ReservationDto(reservation.getId(),conversionPropertyToReservationDTO(reservation.getProperty()),conversionUserToUserForReservationDTO(reservation.getUser()),reservation.getStart_date(),reservation.getEnd_date())).collect(Collectors.toList());
	}

	@Override
	public List<ReservationDto> getReservationsForProperty(int id) {
		return repository.getReservationsForProperty(id).stream().map(reservation-> new ReservationDto(reservation.getId(),conversionPropertyToReservationDTO(reservation.getProperty()),conversionUserToUserForReservationDTO(reservation.getUser()),reservation.getStart_date(),reservation.getEnd_date())).collect(Collectors.toList());
	}

}
