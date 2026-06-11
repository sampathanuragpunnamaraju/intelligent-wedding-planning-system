package com.eventmanagement.dto.api;

import java.util.List;

public record ApiError(
		String code,
		String message,
		List<FieldValidationError> fields
) {

	public static ApiError of(String code, String message) {
		return new ApiError(code, message, List.of());
	}

	public static ApiError withFields(String code, String message, List<FieldValidationError> fields) {
		return new ApiError(code, message, List.copyOf(fields));
	}
}
