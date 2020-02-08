package com.messenger.dao;

import java.util.List;

import com.messenger.model.User;

public interface UserDao {
	
	List<User> get();
	
	User get(String username);
	
	void save(User user);
	
	String delete(String username);

	void updateUserDetails(User user);

	int updateProfileImage(String profileImage, String username);

}
