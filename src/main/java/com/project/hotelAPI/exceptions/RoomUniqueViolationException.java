package com.project.hotelAPI.exceptions;

public class RoomUniqueViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public RoomUniqueViolationException(String message) {
		super(message);
	}
}
