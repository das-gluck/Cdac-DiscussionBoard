package com.example.cdb.service;

import java.util.List;
import java.util.Optional;

import com.example.cdb.entity.User;

public interface UserService {

	public User insert(User user);
	public List<User> getAll();
	public Optional<User> getUserById(Long id);
	public void deleteUserById(Long id);
	public User updateUser(User user);
	
}
