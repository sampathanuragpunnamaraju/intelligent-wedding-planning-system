package com.eventmanagement.dto.api;

import java.time.LocalDateTime;

public record ApiResponse<T>(
		boolean success,
		T data,
		ApiError error,
		LocalDateTime timestamp,
		String path
) {

	public static <T> ApiResponse<T> success(T data, String path) {
		return new ApiResponse<>(true, data, null, LocalDateTime.now(), path);
	}

	public static <T> ApiResponse<T> failure(ApiError error, String path) {
		return new ApiResponse<>(false, null, error, LocalDateTime.now(), path);
	}
}
