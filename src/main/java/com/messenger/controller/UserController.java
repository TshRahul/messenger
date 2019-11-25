package com.messenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.model.User;
import com.messenger.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/{username}")
	public User getAllUsers(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	@PostMapping("/register")
	public User addUser(@RequestBody User user) {
		
		userService.addUser(user);
		return user;
		
	}
	
	@PutMapping("/user/{username}")
	public User updateUserDetails(@PathVariable String username ,@RequestBody User user) {
		user.setUsername(username);
		userService.updateUser(user);
		return user;
		
	}
	
	@DeleteMapping("/user/{username}")
	public String removeUser(@PathVariable String username) {
		
		return userService.deleteUser(username);
		
	}
	
}





