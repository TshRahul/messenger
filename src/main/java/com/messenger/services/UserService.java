package com.messenger.services;

import java.util.List;

import com.messenger.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUser(String username);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	String deleteUser(String username);

}
