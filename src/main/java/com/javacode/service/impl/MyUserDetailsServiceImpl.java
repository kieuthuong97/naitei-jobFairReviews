package com.javacode.service.impl;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.javacode.entities.User;

public class MyUserDetailsServiceImpl extends BaseServiceImpl implements UserDetailsService {
		
	private static final Logger logger = Logger.getLogger(MyUserDetailsServiceImpl.class);
	
	
	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		try {
			User user = getUserDAO().findByEmail(email);		
			if (user != null) {
				return createUserDetails(user);
			}
			return null;
		} catch (Exception e) {
			logger.error("have error in " + e);
			return null;
		}

	}

	private UserDetails createUserDetails(User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, user.getAuthorities());
	}
	


}