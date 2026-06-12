package com.eventmanagement.service;

import com.eventmanagement.dto.auth.AuthResponse;
import com.eventmanagement.dto.auth.LoginRequest;
import com.eventmanagement.dto.auth.RegisterRequest;
import com.eventmanagement.entity.User;
import com.eventmanagement.exception.AuthenticationFailedException;
import com.eventmanagement.exception.ConflictException;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.security.JwtService;
import java.time.Instant;
import java.util.Locale;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

	private static final String TOKEN_TYPE = "Bearer";

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			JwtService jwtService
	) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Transactional
	public AuthResponse register(RegisterRequest request) {
		String email = normalizeEmail(request.email());
		if (userRepository.existsByEmail(email)) {
			throw new ConflictException("Email is already registered");
		}

		User user = new User();
		user.setName(request.name().trim());
		user.setEmail(email);
		user.setPasswordHash(passwordEncoder.encode(request.password()));

		User savedUser = userRepository.save(user);

		return createAuthResponse(savedUser);
	}

	@Transactional(readOnly = true)
	public AuthResponse login(LoginRequest request) {
		String email = normalizeEmail(request.email());
		User user = userRepository.findByEmail(email)
				.orElseThrow(AuthenticationFailedException::new);

		if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
			throw new AuthenticationFailedException();
		}

		return createAuthResponse(user);
	}

	private AuthResponse createAuthResponse(User user) {
		String token = jwtService.generateToken(user.getId(), user.getEmail());
		Instant expiresAt = jwtService.getExpiresAt(token);

		return new AuthResponse(token, TOKEN_TYPE, expiresAt, user.getId(), user.getEmail());
	}

	private String normalizeEmail(String email) {
		return email.trim().toLowerCase(Locale.ROOT);
	}
}
