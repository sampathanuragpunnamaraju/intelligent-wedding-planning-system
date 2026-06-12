package com.eventmanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eventmanagement.dto.auth.AuthResponse;
import com.eventmanagement.exception.AuthenticationFailedException;
import com.eventmanagement.exception.GlobalExceptionHandler;
import com.eventmanagement.service.AuthService;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({ AuthController.class, GlobalExceptionHandler.class })
class AuthControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private AuthService authService;

	@Test
	void registerReturnsCreatedAuthResponseEnvelope() throws Exception {
		when(authService.register(any())).thenReturn(new AuthResponse(
				"jwt-token",
				"Bearer",
				Instant.parse("2026-06-11T12:00:00Z"),
				1L,
				"alex@example.com"
		));

		mockMvc.perform(post("/api/v1/auth/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"name":"Alex Customer","email":"alex@example.com","password":"plain-password"}\
								"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.data.token").value("jwt-token"))
				.andExpect(jsonPath("$.data.tokenType").value("Bearer"))
				.andExpect(jsonPath("$.data.userId").value(1))
				.andExpect(jsonPath("$.data.email").value("alex@example.com"))
				.andExpect(jsonPath("$.path").value("/api/v1/auth/register"));
	}

	@Test
	void loginReturnsAuthResponseEnvelope() throws Exception {
		when(authService.login(any())).thenReturn(new AuthResponse(
				"jwt-token",
				"Bearer",
				Instant.parse("2026-06-11T12:00:00Z"),
				1L,
				"alex@example.com"
		));

		mockMvc.perform(post("/api/v1/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"email":"alex@example.com","password":"plain-password"}\
								"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.data.token").value("jwt-token"))
				.andExpect(jsonPath("$.data.tokenType").value("Bearer"))
				.andExpect(jsonPath("$.path").value("/api/v1/auth/login"));
	}

	@Test
	void loginFailureUsesStandardErrorEnvelope() throws Exception {
		when(authService.login(any())).thenThrow(new AuthenticationFailedException());

		mockMvc.perform(post("/api/v1/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"email":"alex@example.com","password":"wrong-password"}\
								"""))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.success").value(false))
				.andExpect(jsonPath("$.error.code").value("AUTHENTICATION_FAILED"))
				.andExpect(jsonPath("$.error.message").value("Invalid email or password"))
				.andExpect(jsonPath("$.path").value("/api/v1/auth/login"));
	}

	@Test
	void invalidRegisterRequestUsesValidationErrorEnvelope() throws Exception {
		mockMvc.perform(post("/api/v1/auth/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"name":"","email":"not-an-email","password":"short"}\
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.success").value(false))
				.andExpect(jsonPath("$.error.code").value("VALIDATION_FAILED"))
				.andExpect(jsonPath("$.path").value("/api/v1/auth/register"));
	}
}
