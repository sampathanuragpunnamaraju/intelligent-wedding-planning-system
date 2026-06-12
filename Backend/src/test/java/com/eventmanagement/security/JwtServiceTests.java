package com.eventmanagement.security;

import static org.assertj.core.api.Assertions.assertThat;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtServiceTests {

	private static final String JWT_SECRET = "test-jwt-secret-with-at-least-32-characters";

	private JwtService jwtService;

	@BeforeEach
	void setUp() {
		JwtProperties jwtProperties = new JwtProperties();
		jwtProperties.setSecret(JWT_SECRET);
		jwtProperties.setExpirationMinutes(60);
		jwtService = new JwtService(jwtProperties);
	}

	@Test
	void generatedTokenUsesUserIdSubjectAndEmailClaim() {
		String token = jwtService.generateToken(42L, "alex@example.com");

		assertThat(jwtService.isTokenValid(token)).isTrue();
		assertThat(jwtService.getUserId(token)).isEqualTo(42L);
		assertThat(jwtService.getEmail(token)).contains("alex@example.com");
		assertThat(jwtService.getExpiresAt(token)).isNotNull();
	}

	@Test
	void generatedTokenDoesNotIncludeNameClaim() {
		String token = jwtService.generateToken(42L, "alex@example.com");
		Claims claims = Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
				.build()
				.parseSignedClaims(token)
				.getPayload();

		assertThat(claims.getSubject()).isEqualTo("42");
		assertThat(claims.get("email", String.class)).isEqualTo("alex@example.com");
		assertThat(claims).doesNotContainKey("name");
	}

	@Test
	void invalidTokenIsRejected() {
		assertThat(jwtService.isTokenValid("not-a-jwt")).isFalse();
	}
}
