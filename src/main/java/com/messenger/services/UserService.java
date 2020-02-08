package com.messenger.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.messenger.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUser(String username);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	String deleteUser(String username);
	
	int store(MultipartFile file, String username, HttpSession session);

}
