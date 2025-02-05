package com.example.cdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdb.entity.User;
import com.example.cdb.service.UserService;
import com.example.cdb.entity.UserDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User createdUser = userService.insert(user);
		return ResponseEntity.ok(createdUser);
		//return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser() {
		
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			System.out.println("No data is present");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	    return ResponseEntity.ok(users);
	    //return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		
		Optional<User> user = userService.getUserById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}else {
			System.out.println("User is not present");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	@DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
    }
	
	
	
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		Optional<User> existingUser = userService.getUserById(user.getUser_id());
		if(existingUser.isPresent()) {
			User updatedUser = userService.updateUser(user);
			return ResponseEntity.ok(updatedUser);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/users/{userId}/follow/{followId}")
    public ResponseEntity<Void> followUser(@PathVariable Long userId, @PathVariable Long followId) {
        userService.followUser(userId, followId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    @PostMapping("/users/{userId}/unfollow/{unfollowId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long userId, @PathVariable Long unfollowId) {
        userService.unfollowUser(userId, unfollowId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
    @GetMapping("/users/{userId}/followers")
    public ResponseEntity<List<UserDTO>> getFollowers(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getFollowers(userId), HttpStatus.OK);
    }

    
    @GetMapping("/users/{userId}/following")
    public ResponseEntity<List<UserDTO>> getFollowing(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getFollowing(userId), HttpStatus.OK);
    }
}
