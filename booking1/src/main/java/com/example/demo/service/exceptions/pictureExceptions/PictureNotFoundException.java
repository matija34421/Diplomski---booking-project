package com.example.demo.service.exceptions.pictureExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Slika nije pronadjena")
public class PictureNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PictureNotFoundException(int pictureId) {
		super("Slika sa id-om: "+pictureId+" nije pronadjena");
	}
}
