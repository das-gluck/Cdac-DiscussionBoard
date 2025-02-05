package com.example.cdb.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cdb.entity.Role;
import com.example.cdb.entity.User;
import com.example.cdb.repository.UserRepository;
import com.example.cdb.entity.UserDTO;

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
		List<Role> roles = List.of( new Role("ROLE_USER", user));
		user.setRoleList(roles);
		user.setPassword("{noop}"+user.getPassword());
		return userRepository.save(user);
	}
	
	@Override
	public void followUser(Long userId, Long followId) {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> followUserOpt = userRepository.findById(followId);
        
        if (userOpt.isPresent() && followUserOpt.isPresent()) {
            User user = userOpt.get();
            User followUser = followUserOpt.get();
            
            user.getFollowing().add(followUser);  // Adds followUser to the following list of the current user
            followUser.getFollowers().add(user);  // Adds current user to the follower list of followUser
            
            userRepository.save(user);  // Saves the updated user object in the repository
            userRepository.save(followUser);  // Saves the updated followUser object in the repository
        }
	}


	@Override
	public void unfollowUser(Long userId, Long unfollowId) {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> unfollowUserOpt = userRepository.findById(unfollowId);
        
        if (userOpt.isPresent() && unfollowUserOpt.isPresent()) {
            User user = userOpt.get();
            User unfollowUser = unfollowUserOpt.get();
            
            user.getFollowing().remove(unfollowUser);  // Removes the unfollowUser from the following list of the current user
            unfollowUser.getFollowers().remove(user);  // Removes current user from the follower list of unfollowUser
            
            userRepository.save(user);  // Saves the updated user object in the repository
            userRepository.save(unfollowUser);  // Saves the updated unfollowUser object in the repository
        }
	}


	@Override
	public List<UserDTO> getFollowers(Long userId) {
	    Optional<User> userOpt = userRepository.findById(userId);
	    return userOpt.map(user -> user.getFollowers().stream()
	            .map(follower -> new UserDTO(
	                follower.getUser_id(), 
	                follower.getFirstName(), 
	                follower.getLastName()
	            ))
	            .collect(Collectors.toList()))
	            .orElse(Collections.emptyList());
	}

	@Override
	public List<UserDTO> getFollowing(Long userId) {
	    Optional<User> userOpt = userRepository.findById(userId);
	    return userOpt.map(user -> user.getFollowing().stream()
	            .map(following -> new UserDTO(following.getUser_id(), following.getFirstName(), following.getLastName()))
	            .collect(Collectors.toList()))
	            .orElse(Collections.emptyList());


	}
}	
