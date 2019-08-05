package com.javacode.service.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import com.javacode.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuth extends org.springframework.security.core.userdetails.User {
	private User user;
	private static final long serialVersionUID = 1L;

	public UserAuth(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			User user) {
		// TODO Auto-generated constructor stub
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
	}
}
