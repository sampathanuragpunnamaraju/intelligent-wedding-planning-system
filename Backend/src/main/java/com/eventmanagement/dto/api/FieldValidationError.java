package com.eventmanagement.dto.api;

public record FieldValidationError(
		String field,
		String message
) {
}
