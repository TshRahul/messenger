package com.messenger.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.messenger.exceptions.RecordNotFoundException;
import com.messenger.model.Message;
import com.messenger.repository.MessageRepo;
import com.messenger.utilities.BasicUtility;

@Repository
public class MessageDaoImpl implements MessageDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	MessageRepo messageRepo;
	
	BasicUtility baseUtil = new BasicUtility();

	@Override
	public List<Message> get() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Message> query = currentSession.createQuery("from Message", Message.class);
		 List<Message> messages = query.getResultList();
		 System.out.println(messages);
		 return messages;
		
	}

	@Override
	public List<Message> get(String username) {
		List<Message> messages = new ArrayList<Message>();
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			Query query = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isUserAlreadyExits").replace("%username%",username));
		    
			List isUserExists = query.getResultList();
			if(isUserExists.get(0).toString().equals("false")) {
				throw new RecordNotFoundException("The user with user name : " + username + " is not present") ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {

		// Query query = currentSession.createQuery(baseUtil.getValuesFromPropertyFile("getMessagesByUsername").replace("%author%",username), Message.class);
		 messages = messageRepo.findByAuthor(username);
		
		System.out.println(messages);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return messages;
	}

	@Override
	public Message get(long id) {
		Message message = messageRepo.findById(id);
		
		if(message == null) {
			throw new RecordNotFoundException("The message with message id : " + id + " is not present");
		}
		
		return message;
	}

	@Override
	public void save(Message message) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			Query query = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isUserAlreadyExits").replace("%username%",message.getAuthor()));
		    
			List isUserExists = query.getResultList();
			if(isUserExists.get(0).toString().equals("false")) {
				throw new RecordNotFoundException("The user with user name : " + message.getAuthor() + " is not present") ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		Date date=Calendar.getInstance().getTime();  
		
		   message.setCreated_date(date);
		   message.setModified_date(null);
		   message.setNoOfLikes(0);
		   message.setNoOfComments(0);
		   message.setDeleted(false);
		currentSession.saveOrUpdate(message);
		currentSession.close();
		
	}

	@Override
	public void updateMessage(Message message) {
		
//           Message message2 = messageRepo.findById(message.getId());
//		
//		if(message2 == null) {
//			throw new RecordNotFoundException("The message with message id : " + message.getId() + " is not present");
//		}
		Session currentSession = entityManager.unwrap(Session.class);
		Date date=Calendar.getInstance().getTime();  
		 message.setModified_date(date);
		 currentSession.saveOrUpdate(message);
	}

	@Override
	public String delete(long id) {
		
		  Message message = messageRepo.findById(id);
			
			if(message == null) {
				throw new RecordNotFoundException("The message with message id : " + id + " is not present");
			}
		
			Message message2 = messageRepo.findById(id);
			message2.setDeleted(true);
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(message2);
			
			return "The message with message id : "+ id +" is deleted successfully ";
		
	}
	
	
	
	

}
