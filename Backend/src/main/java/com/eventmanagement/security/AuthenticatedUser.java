package com.eventmanagement.security;

import com.eventmanagement.entity.User;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser implements UserDetails {

	private final Long id;
	private final String email;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;

	public AuthenticatedUser(
			Long id,
			String email,
			String password,
			Collection<? extends GrantedAuthority> authorities
	) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = List.copyOf(authorities);
	}

	public static AuthenticatedUser from(User user) {
		return new AuthenticatedUser(
				user.getId(),
				user.getEmail(),
				user.getPasswordHash(),
				List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
		);
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
}
