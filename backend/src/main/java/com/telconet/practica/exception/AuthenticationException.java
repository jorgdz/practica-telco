package com.telconet.practica.exception;

public class AuthenticationException extends RuntimeException {
    
    public AuthenticationException(String context) {
		super(context);
	}
    
    public AuthenticationException() {
		super("Bad credentials");
	}
}
