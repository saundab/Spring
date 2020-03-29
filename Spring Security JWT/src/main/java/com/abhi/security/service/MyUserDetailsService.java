package com.abhi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhi.security.domain.User;
import com.abhi.security.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//return User.withUsername("user").password("user").roles("USER").build();
		//return new MyUserDetails(username);
		User user = userRepository.findByUserName(username);
		
		return new MyUserDetails(user);
	}

}
