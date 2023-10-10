package com.project.hotelAPI.web.exceptions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String path;
	private String method;
	private int status;
	private String statusText;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> errors;
	
	public StandardError() {
	}
	
	public StandardError(HttpServletRequest request, HttpStatus status, String message) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
	}
	
	public StandardError(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
		addErros(result);
	}
	
	private void addErros(BindingResult result) {
		this.errors = new HashMap<>();
		for(FieldError error: result.getFieldErrors()) {
			this.errors.put(error.getField(), error.getDefaultMessage());
		}
	}
	
}
