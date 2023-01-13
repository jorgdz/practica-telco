package com.telconet.practica.controller.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telconet.practica.util.ErrorResponse;

public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest arg0, HttpServletResponse arg1, AccessDeniedException arg2)
            throws IOException, ServletException {
        ErrorResponse error = new ErrorResponse(
            "No puedes acceder a este recurso.", 
            (Exception) arg2, 
            arg0.getRequestURI(), 
            403
        );

        arg1.getWriter().write( new ObjectMapper().writeValueAsString(error) );
        arg1.setStatus(403);
        arg1.setContentType("application/json");
    }
    
}
