package com.example.demo.service.exceptions.userExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Korisnik nije pronadjen")
public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(int userId) {
		super("Korisnik sa id-om: "+userId+" nije pronadjen");
	}

	public UserNotFoundException(String email) {
		super("Korisnik sa email-om: "+email+" nije pronadjen");
	}
}
