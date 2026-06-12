package com.eventmanagement.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends DomainException {

	public AuthenticationFailedException() {
		super("AUTHENTICATION_FAILED", "Invalid email or password", HttpStatus.UNAUTHORIZED);
	}
}
