package com.example.demo.service.exceptions.propertyExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Oglas nije pronadjen")
public class PropertyNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PropertyNotFoundException(int propertyId) {
		super("Oglas sa id-om: "+propertyId+" nije pronadjen");
	}
}
