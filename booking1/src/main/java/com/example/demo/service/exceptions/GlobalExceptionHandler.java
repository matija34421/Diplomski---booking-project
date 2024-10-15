package com.example.demo.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.service.exceptions.conversationException.ConversationNotFoundException;
import com.example.demo.service.exceptions.messageException.MessageNotFoundException;
import com.example.demo.service.exceptions.pictureExceptions.PictureIllegalArgumentException;
import com.example.demo.service.exceptions.pictureExceptions.PictureNotFoundException;
import com.example.demo.service.exceptions.propertyExceptions.PropertyIllegalArgumentException;
import com.example.demo.service.exceptions.propertyExceptions.PropertyNotFoundException;
import com.example.demo.service.exceptions.reservationExceptions.ReservationIllegalArgumentException;
import com.example.demo.service.exceptions.reservationExceptions.ReservationNotFoundException;
import com.example.demo.service.exceptions.userExceptions.UserIllegalArgumentException;
import com.example.demo.service.exceptions.userExceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(UserIllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleUserIllegalArgumentException(UserIllegalArgumentException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(PropertyNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePropertyNotFoundException(PropertyNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(PropertyIllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handlePropertyIllegalArgumentException(PropertyIllegalArgumentException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(ReservationIllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleReservationIllegalArgumentException(ReservationIllegalArgumentException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReservationNotFoundException(ReservationNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(PictureIllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handlePictureIllegalArgumentException(PictureIllegalArgumentException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(PictureNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePictureNotFoundException(PictureNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(ConversationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleConversationNotFoundException(ConversationNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(MessageNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMessageNotFoundException(MessageNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(ReviewsNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReviewsNotFoundException(ReviewsNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
}
