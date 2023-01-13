package com.telconet.practica.service.jwt;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;

public interface JWTService {

    /**
	 * Generar un nuevo token para el usuario
	 * @param userAuth
	 * @return token alfanumerico
	 */
	String generateAuthToken(User userAuth);

	/**
	 * Validar el token
	 * @param token token alfanumerico
	 * @return true si el token es valido o de lo contrario false
	 */
	boolean isValidToken(String token);

	/**
	 * Proporciona claims adicionales para el token
	 * @param token token alfanumerico
	 * @return token claims
	 */
	Claims getTokenClaims(String token);
}
