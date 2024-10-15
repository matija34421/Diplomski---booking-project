package com.example.demo.service.exceptions.messageException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Chat nije pronadjen")
public class MessageNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MessageNotFoundException(int messageId) {
		super("Poruka sa id-om: "+messageId+" nije pronadjen");
	}
}
