package com.eventmanagement.dto.auth;

import java.time.Instant;

public record AuthResponse(
		String token,
		String tokenType,
		Instant expiresAt,
		Long userId,
		String email
) {
}
