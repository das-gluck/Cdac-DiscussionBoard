package com.example.cdb.service;

import java.util.List;
import java.util.Optional;

import com.example.cdb.entity.User;
import com.example.cdb.entity.UserDTO;

public interface UserService {

	public User insert(User user);
	public List<User> getAll();
	public Optional<User> getUserById(Long id);
	public void deleteUserById(Long id);
	public User updateUser(User user);
	public void followUser(Long userId, Long followId);
    public void unfollowUser(Long userId, Long unfollowId);


    public List<UserDTO> getFollowers(Long userId);
    public List<UserDTO> getFollowing(Long userId);
	
}
