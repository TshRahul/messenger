package com.messenger.dao;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.messenger.exceptions.RecordAlreadyPresent;
import com.messenger.exceptions.RecordNotFoundException;
import com.messenger.model.User;
import com.messenger.utilities.BasicUtility;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	EntityManager entityManager;
	
	BasicUtility baseUtil = new BasicUtility();

	@Override
	public List<User> get() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		 Query<User> query = currentSession.createQuery("from User", User.class);
		 List<User> users = query.getResultList();
		 return users;
		         
	}

	@Override
	public User get(String username) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, username);
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			Query query = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isUserAlreadyExits").replace("%username%",user.getUsername()));
		    
			List isUserExists = query.getResultList();
			if(isUserExists.get(0).toString().equals("true")) {
				throw new RecordAlreadyPresent("The user with user name : " + user.getUsername() + " is already present") ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		 String  originalPassword = user.getPassword();
	        String generatedSecuredPasswordHash = Sha512DigestUtils.shaHex(originalPassword);
	        System.out.println(generatedSecuredPasswordHash);
	        user.setPassword(generatedSecuredPasswordHash);
		Date date=Calendar.getInstance().getTime();  
		
		   user.setCreation_date(date);
		   user.setActive(true);
		currentSession.saveOrUpdate(user);
		currentSession.close();
		
	}
	
	@Override
	public void updateUserDetails(User user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
		
	}

	@Override
	public String delete(String username) {
		
		Session currentSession = entityManager.unwrap(Session.class);
	//	String user_id = String.valueOf(id);
		try {
			Query query = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isUserPresent").replace("%username%",username));
		    
			List isUserExists = query.getResultList();
			if(isUserExists.get(0).toString().equals("false")) {
				throw new RecordNotFoundException("The user with user name : " + username + " is already present") ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		currentSession = entityManager.unwrap(Session.class);
		try {
		Query query = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("updateTheActiveFieldOfUser").replace("%username%",username));
		query.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
		
		return "user with username : " + username + " is deleted successfully";
	
	}

}
