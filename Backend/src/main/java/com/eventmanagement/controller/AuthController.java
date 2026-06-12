package com.eventmanagement.controller;

import com.eventmanagement.dto.api.ApiResponse;
import com.eventmanagement.dto.auth.AuthResponse;
import com.eventmanagement.dto.auth.LoginRequest;
import com.eventmanagement.dto.auth.RegisterRequest;
import com.eventmanagement.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoutes.API_PREFIX + "/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponse>> register(
			@Valid @RequestBody RegisterRequest request,
			HttpServletRequest httpRequest
	) {
		AuthResponse response = authService.register(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ApiResponse.success(response, httpRequest.getRequestURI()));
	}

	@PostMapping("/login")
	public ApiResponse<AuthResponse> login(
			@Valid @RequestBody LoginRequest request,
			HttpServletRequest httpRequest
	) {
		return ApiResponse.success(authService.login(request), httpRequest.getRequestURI());
	}
}
