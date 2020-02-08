package com.messenger.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.messenger.model.Comment;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.messenger.exceptions.BadRequestException;
import com.messenger.exceptions.RecordNotFoundException;
import com.messenger.model.Message;
import com.messenger.repository.MessageRepo;
import com.messenger.utilities.BasicUtility;

@Repository
public class MessageDaoImpl implements MessageDao {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private MessageRepo messageRepo;

	@Autowired
	private CommentDao commentDao;

	private BasicUtility baseUtil = new BasicUtility();

	@Override
	public List<Message> get() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Message> query = currentSession.createQuery("from Message where isdeleted = false", Message.class);
		 List<Message> messages = query.getResultList();

		for(Message message : messages){
			List<Comment> comments = commentDao.getcomments(message.getId());
                List<Comment> commentToAdd = new ArrayList<>();
                 for(Comment comment : comments){
					 System.out.println(comment);
                 	if(!comment.isIs_deleted()){
						commentToAdd.add(comment);
					}
				 }
			message.setComments(commentToAdd);
		 }



		 System.out.println(messages);
		 return messages;
		
	}

	@Override
	public List<Message> get(String username) {
		List<Message> messages = new ArrayList<>();
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

			for(Message message : messages){
				List<Comment> comments = commentDao.getcomments(message.getId());
				List<Comment> commentToAdd = new ArrayList<>();
				for(Comment comment : comments){
					System.out.println(comment);
					if(!comment.isIs_deleted()){
						commentToAdd.add(comment);
					}
				}
				message.setComments(commentToAdd);
			}
		
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
		List<Comment> comments = commentDao.getcomments(message.getId());
		List<Comment> commentToAdd = new ArrayList<>();
		for(Comment comment : comments){
			System.out.println(comment);
			if(!comment.isIs_deleted()){
				commentToAdd.add(comment);
			}
		}
		message.setComments(commentToAdd);
		return message;
	}

	@Override
	public void save(Message message) {
		if(message.getMessage().isEmpty()) {
			throw new BadRequestException("Empty message is not accepted");
		}
		
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
		Message messageByID = messageRepo.findById(message.getId());
		if(messageByID == null) {
			throw new RecordNotFoundException("The message with message id : " + message.getId() + " is not present");
		}
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			Date date=Calendar.getInstance().getTime();  
		Query updateMessage = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("updateMessageForMessageId").replace("%message%",message.getMessage()).replace("%modifiedDate%", date.toString()).replace("%messageId%", String.valueOf( message.getId())));
		updateMessage.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message delete(long id) {
		
		  Message message = messageRepo.findById(id);
			
			if(message == null) {
				throw new RecordNotFoundException("The message with message id : " + id + " is not present");
			}
		Session currentSession = entityManager.unwrap(Session.class);
            List<Comment> comments = message.getComments();

			if(comments != null){
                  for(Comment comment : comments){
                       comment.setIs_deleted(true);
                       currentSession.saveOrUpdate(comment);
				  }
			}
		
			Message message2 = messageRepo.findById(id);
			message2.setDeleted(true);

			currentSession.saveOrUpdate(message2);
			
			return message;
		
	}
	
	
	
	

}
