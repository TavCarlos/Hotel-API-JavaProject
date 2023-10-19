package com.project.hotelAPI.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.hotelAPI.exceptions.CpfUniqueViolationException;
import com.project.hotelAPI.exceptions.EntityNotFoundException;
import com.project.hotelAPI.exceptions.InvalidDateException;
import com.project.hotelAPI.exceptions.RoomUniqueViolationException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(HttpServletRequest request, BindingResult result){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new StandardError(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)", result));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> EntityNotFound(EntityNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new StandardError(request, HttpStatus.NOT_FOUND, ex.getMessage()));
	}
	
	@ExceptionHandler({CpfUniqueViolationException.class, RoomUniqueViolationException.class})
	public ResponseEntity<StandardError> DataIntegrityViolationException(RuntimeException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new StandardError(request, HttpStatus.CONFLICT, ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<StandardError> InvalidDateException(RuntimeException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new StandardError(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
	}
}
