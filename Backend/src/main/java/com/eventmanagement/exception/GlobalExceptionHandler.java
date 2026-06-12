package com.eventmanagement.exception;

import com.eventmanagement.dto.api.ApiError;
import com.eventmanagement.dto.api.ApiResponse;
import com.eventmanagement.dto.api.FieldValidationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidationFailure(
			MethodArgumentNotValidException exception,
			HttpServletRequest request
	) {
		List<FieldValidationError> fields = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(this::toFieldValidationError)
				.toList();

		ApiError error = ApiError.withFields(
				"VALIDATION_FAILED",
				"Request validation failed",
				fields
		);

		return ResponseEntity.badRequest().body(ApiResponse.failure(error, request.getRequestURI()));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(
			ConstraintViolationException exception,
			HttpServletRequest request
	) {
		List<FieldValidationError> fields = exception.getConstraintViolations()
				.stream()
				.map(violation -> new FieldValidationError(
						violation.getPropertyPath().toString(),
						violation.getMessage()
				))
				.toList();

		ApiError error = ApiError.withFields(
				"VALIDATION_FAILED",
				"Request validation failed",
				fields
		);

		return ResponseEntity.badRequest().body(ApiResponse.failure(error, request.getRequestURI()));
	}

	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ApiResponse<Void>> handleAuthenticationFailure(
			AuthenticationFailedException exception,
			HttpServletRequest request
	) {
		ApiError error = ApiError.of(exception.getErrorCode(), exception.getMessage());

		return ResponseEntity
				.status(exception.getStatus())
				.body(ApiResponse.failure(error, request.getRequestURI()));
	}

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ApiResponse<Void>> handleDomainException(
			DomainException exception,
			HttpServletRequest request
	) {
		ApiError error = ApiError.of(exception.getErrorCode(), exception.getMessage());

		return ResponseEntity
				.status(exception.getStatus())
				.body(ApiResponse.failure(error, request.getRequestURI()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(
			Exception exception,
			HttpServletRequest request
	) {
		ApiError error = ApiError.of(
				"INTERNAL_SERVER_ERROR",
				"An unexpected error occurred"
		);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.failure(error, request.getRequestURI()));
	}

	private FieldValidationError toFieldValidationError(FieldError fieldError) {
		return new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage());
	}
}
