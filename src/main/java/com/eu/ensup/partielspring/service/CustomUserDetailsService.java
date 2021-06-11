package com.eu.ensup.partielspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eu.ensup.partielspring.domaine.CustomUserDetails;
import com.eu.ensup.partielspring.domaine.User;

public class CustomUserDetailsService implements UserDetailsService
{
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = new User("admin", "admin");
		
		if (!username.equals(user.getLogin()))
		{
            throw new UsernameNotFoundException(username);
        }
		
        return new CustomUserDetails(user);
	}
}
