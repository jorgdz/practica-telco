package com.telconet.practica.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.telconet.practica.util.ErrorResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class Handler {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
    	NoHandlerFoundException.class,
    	NotFoundException.class
    })
    public ErrorResponse notFoundException(HttpServletRequest request, Exception exception) {
		if(exception instanceof NoHandlerFoundException || exception instanceof NotFoundException)
            return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.NOT_FOUND.value());
            
        return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.NOT_FOUND.value());
    }

	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
    	ConflictException.class
    })
	public ErrorResponse conflictException (HttpServletRequest request, Exception exception) {
		return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.NOT_FOUND.value());
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
    		BadRequestException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class
    })
    public ErrorResponse badRequestException(HttpServletRequest request, Exception exception) {
		if(exception instanceof HttpRequestMethodNotSupportedException)
            return new ErrorResponse("No se admite peticiones de tipo " + ((HttpRequestMethodNotSupportedException) exception).getMethod() + ", solo se admite solicitudes de tipo " + Arrays.toString(((HttpRequestMethodNotSupportedException) exception).getSupportedMethods()),
                exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    	
		if(exception instanceof MissingRequestHeaderException)
			return new ErrorResponse("El valor de la cabecera " + ((MissingRequestHeaderException) exception).getHeaderName() + " no es válido.", 
	     			exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
		
		if(exception instanceof MissingServletRequestParameterException)
			return new ErrorResponse("El parámetro " + ((MissingServletRequestParameterException) exception).getParameterName() + " es requerido.", 
	     			exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value()); 
	        
    	if (exception instanceof MethodArgumentTypeMismatchException) 
        	return new ErrorResponse("No se puede convertir el valor " + ((MethodArgumentTypeMismatchException) exception).getValue() 
                + " a un tipo de dato " + ((MethodArgumentTypeMismatchException) exception).getRequiredType().getSimpleName(), 
                exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
        
    	if(exception instanceof MethodArgumentNotValidException) {
    		BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
    		
    		List<FieldError> errors = bindingResult.getFieldErrors();
    		List<String> listErrors = new ArrayList<String>();
    		
    		errors.forEach(e -> listErrors.add(e.getDefaultMessage()));
  
    		return new ErrorResponse (listErrors, exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    	}
    	
    	if(exception instanceof HttpMessageNotReadableException) {
    		HttpMessageNotReadableException ex = (HttpMessageNotReadableException) exception;
    		return new ErrorResponse(ex.getMostSpecificCause().getLocalizedMessage(), exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    	}
    	
    	return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
    	ArithmeticException.class,
    	Exception.class,
    })  
    public ErrorResponse unexpectedException(HttpServletRequest request, Exception exception) {      
    	return new ErrorResponse(exception, request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
