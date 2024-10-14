package com.example.demo.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recenzije nisu pronadjene")
public class ReviewsNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ReviewsNotFoundException(int propertyId) {
		super("Recenzije sa ID-em objekta: " + propertyId + " nisu pronadjene");
	}
}
