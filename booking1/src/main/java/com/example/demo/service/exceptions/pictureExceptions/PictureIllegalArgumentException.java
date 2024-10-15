package com.example.demo.service.exceptions.pictureExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.service.services.dto.pictureDto.PictureCreateDTO;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Slika oglasa nije pronadjena")
public class PictureIllegalArgumentException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PictureIllegalArgumentException(int pictureId) {
		super("ID slike mora biti veci od 0");
	}

	public PictureIllegalArgumentException(PictureCreateDTO picture) {
		super("Lose prosledjena slika!");
	}
}
