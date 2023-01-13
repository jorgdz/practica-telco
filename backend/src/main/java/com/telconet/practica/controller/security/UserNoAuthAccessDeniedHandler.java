package com.telconet.practica.controller.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telconet.practica.util.ErrorResponse;

public class UserNoAuthAccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authEx)
            throws IOException, ServletException {
        ErrorResponse error = new ErrorResponse(
            "El api key es requerido para realizar esta solicitud.",
            (Exception) authEx, 
            req.getRequestURI(), 
            403
        );

        res.getWriter().write( new ObjectMapper().writeValueAsString(error) );
        res.setStatus(403);
        res.setContentType("application/json");
    }
}
