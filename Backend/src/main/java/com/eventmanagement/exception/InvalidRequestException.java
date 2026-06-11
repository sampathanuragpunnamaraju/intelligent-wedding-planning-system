package com.eventmanagement.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends DomainException {

	public InvalidRequestException(String message) {
		super("INVALID_REQUEST", message, HttpStatus.BAD_REQUEST);
	}
}
