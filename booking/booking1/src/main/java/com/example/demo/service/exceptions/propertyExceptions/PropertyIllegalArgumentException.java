package com.example.demo.service.exceptions.propertyExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.service.services.dto.propertyDto.PropertyCreateDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyUpdateDTO;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Oglas nije pronadjen")
public class PropertyIllegalArgumentException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PropertyIllegalArgumentException(int propertyId) {
		super("ID oglasa mora biti veci od 0");
	}

	public PropertyIllegalArgumentException(PropertyDTO property) {
		super("Lose prosledjen oglas!");
	}

	public PropertyIllegalArgumentException(PropertyCreateDTO property) {
		super("Lose prosledjen oglas!");
	}

	public PropertyIllegalArgumentException(PropertyUpdateDTO property) {
		super("Lose prosledjen oglas!");
	}
}
