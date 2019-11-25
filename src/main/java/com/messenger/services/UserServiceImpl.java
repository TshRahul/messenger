package com.messenger.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.dao.UserDao;
import com.messenger.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public List<User> getAllUsers() {
		
		return userDao.get();
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return userDao.get(username);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		
		userDao.save(user);
		
	}


	@Override
	@Transactional
	public String deleteUser(String username) {
		
		return userDao.delete(username);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUserDetails(user);
	}
	

}
