package com.eventmanagement.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends DomainException {

	public ConflictException(String message) {
		super("CONFLICT", message, HttpStatus.CONFLICT);
	}
}
