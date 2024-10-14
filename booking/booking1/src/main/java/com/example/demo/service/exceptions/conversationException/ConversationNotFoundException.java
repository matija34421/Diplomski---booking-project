package com.example.demo.service.exceptions.conversationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Chat nije pronadjen")
public class ConversationNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ConversationNotFoundException(int conversationId) {
		super("Chat sa id-om: "+conversationId+" nije pronadjen");
	}
}
