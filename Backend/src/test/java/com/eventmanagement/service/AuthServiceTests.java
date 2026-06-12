package com.eventmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.eventmanagement.dto.auth.AuthResponse;
import com.eventmanagement.dto.auth.LoginRequest;
import com.eventmanagement.dto.auth.RegisterRequest;
import com.eventmanagement.entity.User;
import com.eventmanagement.exception.AuthenticationFailedException;
import com.eventmanagement.exception.ConflictException;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.security.JwtProperties;
import com.eventmanagement.security.JwtService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTests {

	private static final String JWT_SECRET = "test-jwt-secret-with-at-least-32-characters";

	@Mock
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	private AuthService authService;

	@BeforeEach
	void setUp() {
		passwordEncoder = new BCryptPasswordEncoder();
		JwtProperties jwtProperties = new JwtProperties();
		jwtProperties.setSecret(JWT_SECRET);
		jwtProperties.setExpirationMinutes(60);
		authService = new AuthService(userRepository, passwordEncoder, new JwtService(jwtProperties));
	}

	@Test
	void registerHashesPasswordAndReturnsJwt() {
		when(userRepository.existsByEmail("alex@example.com")).thenReturn(false);
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
			User user = invocation.getArgument(0);
			user.setId(10L);
			return user;
		});

		AuthResponse response = authService.register(new RegisterRequest(
				"Alex Customer",
				"  ALEX@example.com ",
				"plain-password"
		));

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(userCaptor.capture());
		User savedUser = userCaptor.getValue();

		assertThat(savedUser.getEmail()).isEqualTo("alex@example.com");
		assertThat(savedUser.getPasswordHash()).isNotEqualTo("plain-password");
		assertThat(passwordEncoder.matches("plain-password", savedUser.getPasswordHash())).isTrue();
		assertThat(response.token()).isNotBlank();
		assertThat(response.tokenType()).isEqualTo("Bearer");
		assertThat(response.userId()).isEqualTo(10L);
		assertThat(response.email()).isEqualTo("alex@example.com");
	}

	@Test
	void registerRejectsDuplicateEmail() {
		when(userRepository.existsByEmail("alex@example.com")).thenReturn(true);

		assertThatThrownBy(() -> authService.register(new RegisterRequest(
				"Alex Customer",
				"alex@example.com",
				"plain-password"
		))).isInstanceOf(ConflictException.class);
	}

	@Test
	void loginVerifiesPasswordAndReturnsJwt() {
		User user = new User();
		user.setId(10L);
		user.setName("Alex Customer");
		user.setEmail("alex@example.com");
		user.setPasswordHash(passwordEncoder.encode("plain-password"));
		when(userRepository.findByEmail("alex@example.com")).thenReturn(Optional.of(user));

		AuthResponse response = authService.login(new LoginRequest(
				" ALEX@example.com ",
				"plain-password"
		));

		assertThat(response.token()).isNotBlank();
		assertThat(response.tokenType()).isEqualTo("Bearer");
		assertThat(response.userId()).isEqualTo(10L);
		assertThat(response.email()).isEqualTo("alex@example.com");
	}

	@Test
	void loginRejectsInvalidPassword() {
		User user = new User();
		user.setId(10L);
		user.setEmail("alex@example.com");
		user.setPasswordHash(passwordEncoder.encode("plain-password"));
		when(userRepository.findByEmail("alex@example.com")).thenReturn(Optional.of(user));

		assertThatThrownBy(() -> authService.login(new LoginRequest(
				"alex@example.com",
				"wrong-password"
		))).isInstanceOf(AuthenticationFailedException.class);
	}

	@Test
	void loginRejectsUnknownEmail() {
		when(userRepository.findByEmail("alex@example.com")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> authService.login(new LoginRequest(
				"alex@example.com",
				"plain-password"
		))).isInstanceOf(AuthenticationFailedException.class);
	}
}
