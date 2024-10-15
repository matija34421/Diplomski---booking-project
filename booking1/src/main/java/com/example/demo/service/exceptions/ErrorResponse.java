package com.example.demo.service.exceptions;

public class ErrorResponse {
	private int statusCode;
    private String message;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    // Getter methods

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
    
}
