package com.example.demo.service.exceptions.userExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.service.services.dto.userDto.UserCreateDTO;
import com.example.demo.service.services.dto.userDto.UserUpdateDTO;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Korisnik nije pronadjen")
public class UserIllegalArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserIllegalArgumentException(int userId) {
		super("ID mora biti veci od 0");
	}
	
	public UserIllegalArgumentException(UserCreateDTO user) {
		super("Lose prosledjen User!");
	}

	public UserIllegalArgumentException(UserUpdateDTO user) {
		super("Lose prosledjen User!");
	}

	public UserIllegalArgumentException(String email) {
		super("User sa identicnim email-om vec postoji!");
	}
}
