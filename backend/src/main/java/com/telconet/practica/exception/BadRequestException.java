package com.telconet.practica.exception;

public class BadRequestException extends RuntimeException {
    
    private static final long serialVersionUID = -4006682371131437146L;

	public BadRequestException(String context) {
		super(context);
	}
}
