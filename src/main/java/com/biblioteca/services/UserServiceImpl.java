package com.biblioteca.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



import com.biblioteca.model.Role;
import com.biblioteca.model.User;
import com.biblioteca.repository.UserRepository;
import com.biblioteca.webControllers.UserRegistrationDto;


@Service
public class UserServiceImpl implements UserService   {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    public User save(UserRegistrationDto registration) {
	        User user = new User();
	        user.setFirstName(registration.getFirstName());
	        user.setLastName(registration.getLastName());
	        user.setEmail(registration.getEmail());
	        user.setPassword(passwordEncoder.encode(registration.getPassword()));
	        user.setRoles(Arrays.asList(new Role("Admin")));
	        return userRepository.save(user);
	    }
	    
	  @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("Nome de Usuario INvalido ou Password.");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
	            user.getPassword(),
	            mapRolesToAuthorities(user.getRoles()));
	    }
	    
	    
	    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
	        return roles.stream()
	            .map(role -> new SimpleGrantedAuthority(role.getName()))
	            .collect(Collectors.toList());
	    }
	
}
