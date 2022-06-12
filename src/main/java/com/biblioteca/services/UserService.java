package com.biblioteca.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.biblioteca.model.User;
import com.biblioteca.webControllers.UserRegistrationDto;

public interface UserService extends UserDetailsService  {

	 User findByEmail(String email);

	    User save(UserRegistrationDto registration);
	
}
