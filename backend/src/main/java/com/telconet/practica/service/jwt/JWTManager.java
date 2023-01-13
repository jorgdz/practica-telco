package com.telconet.practica.service.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JWTManager implements JWTService {

    private final long TOKEN_EXPIRATION = 3600000 * 2;
	private final byte[] SECRET_KEY = "telconet_secure_key".getBytes();

    @Override
    public String generateAuthToken(User userAuth) {
        String token;
		Date currentDate;
		long tokenExpirationTime;
		Claims tokenClaims = null;
        
        try {

            List<String> roles = new ArrayList<>();
			userAuth.getAuthorities().forEach( (role) -> {
				roles.add(role.getAuthority().replaceAll("ROLE_", "")); 
            });

			tokenClaims = Jwts.claims();
			tokenClaims.put("roles", new ObjectMapper().writeValueAsString(roles));

        } catch (Exception e) { 
            e.printStackTrace(); 
        }

        currentDate = new Date();
		tokenExpirationTime = (currentDate.getTime() + TOKEN_EXPIRATION);

        token = Jwts.builder()
			.setClaims(tokenClaims)
			.setSubject(userAuth.getUsername())
			.setIssuedAt(currentDate)
			.setExpiration( new Date(tokenExpirationTime) )
			.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
			.compact();
        
        return token;
    }

    @Override
    public boolean isValidToken(String token) {
        try {
			Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token);

			return true;
		} catch (Exception e) { 
            return false; 
        }
    }

    @Override
    public Claims getTokenClaims(String token) {
        Claims tokenClaims = null;

		try {
			tokenClaims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();

			return tokenClaims;
			
		} catch (ExpiredJwtException | UnsupportedJwtException 
			| MalformedJwtException | SignatureException 
			| IllegalArgumentException e) 
        { 
            return null; 
        }
    }
}
