package com.example.cdb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cdb.entity.Role;
import com.example.cdb.entity.User;
import com.example.cdb.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public User insert(User user) {
		//user.setRoleList(List.of("ROLE_ADMIN","ROLE_USER"));
		//user.setRoleList(List.of("ROLE_USER"));
		
		List<Role> roles = List.of( new Role("ROLE_USER", user));
		user.setRoleList(roles);
		
		user.setPassword("{noop}"+user.getPassword());
		
		// to encode password -> Bcrypt
		//user.setPassword( passwordEncoder.encode(user.getPassword()) );
		
		userRepository.save(user);
		return user;
	}
	
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	} 
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	public User updateUser(User user) { 
		return userRepository.save(user);
	}

}
