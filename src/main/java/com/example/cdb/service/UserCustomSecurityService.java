package com.example.cdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.cdb.exception.NotFoundException;
import com.example.cdb.repository.UserRepository;




@Service
public class UserCustomSecurityService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws NotFoundException {
		return userRepository.findByEmail(username).orElseThrow(()-> new NotFoundException("Not found user with email "+ username));
	}
 
}
