package com.eventmanagement.config;

import com.eventmanagement.controller.ApiRoutes;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.security.JwtAccessDeniedHandler;
import com.eventmanagement.security.JwtAuthenticationEntryPoint;
import com.eventmanagement.security.JwtAuthenticationFilter;
import com.eventmanagement.security.JwtService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@ConditionalOnBean(UserRepository.class)
	public JwtAuthenticationFilter jwtAuthenticationFilter(
			JwtService jwtService,
			UserRepository userRepository
	) {
		return new JwtAuthenticationFilter(jwtService, userRepository);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http,
			ObjectProvider<JwtAuthenticationFilter> jwtAuthenticationFilter,
			JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAccessDeniedHandler accessDeniedHandler
	) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(exception -> exception
						.authenticationEntryPoint(authenticationEntryPoint)
						.accessDeniedHandler(accessDeniedHandler))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(
								ApiRoutes.API_PREFIX + "/auth/register",
								ApiRoutes.API_PREFIX + "/auth/login"
						).permitAll()
						.requestMatchers(ApiRoutes.API_PREFIX + "/**").authenticated()
						.anyRequest().permitAll());

		jwtAuthenticationFilter.ifAvailable(filter ->
				http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class));

		return http.build();
	}
}
