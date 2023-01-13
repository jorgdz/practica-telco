package com.telconet.practica.exception;

public class ConflictException extends RuntimeException {
    
    private static final long serialVersionUID = -4006682371131437146L;

	public ConflictException (String context) {
		super(context);
	}
}
