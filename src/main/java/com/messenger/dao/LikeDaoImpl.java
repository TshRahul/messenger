package com.messenger.dao;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.messenger.exceptions.RecordAlreadyPresent;
import com.messenger.exceptions.RecordNotFoundException;
import com.messenger.model.Like;
import com.messenger.utilities.BasicUtility;

@Repository
public class LikeDaoImpl implements LikeDao{
	
	@Autowired
	EntityManager entityManager;
	
	BasicUtility baseUtil = new BasicUtility();

	@Override
	public void save(Like like, long messageId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			
			Query query1 = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isUserPresent").replace("%username%",like.getLikedBy()));
			List isUserPresent = query1.getResultList();
			if(isUserPresent.get(0).toString().equals("false")) {
				throw new RecordNotFoundException("The user with username: " +like.getLikedBy() + " does not exist" );
			}
			
			Query query2 = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isMessagePresent").replace("%messageId%",String.valueOf(messageId)));
			List isMessagePresent = query2.getResultList();
			if(isMessagePresent.get(0).toString().equals("false")) {
				throw new RecordNotFoundException("The message with message ID : " +messageId + " does not exist" );
			}
			
			
			
			Query query3 = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isLikeReordPresent").replace("%author%", like.getLikedBy()).replace("%messageId%",String.valueOf(messageId)));
		    
			List isLikePresent = query3.getResultList();
			if(isLikePresent.get(0).toString().equals("true")) {
				throw new RecordAlreadyPresent("The like record is already present for this message with the current user");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Date date=Calendar.getInstance().getTime(); 
		like.setCreated_date(date);
		like.setLiked(true);
		like.setMessage_id(messageId);
		currentSession.saveOrUpdate(like);
		try {
		Query getNoOfLikes = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("getNoOfLikes").replace("%messageId%",String.valueOf(messageId)));
		List likes = getNoOfLikes.getResultList();
		String noOfLikes = likes.get(0).toString();
		
		Query updateLikesNo = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("addLikeToMessage").replace("%noOfLikes%",Integer.valueOf(noOfLikes) + 1 + "").replace("%messageId%", String.valueOf(messageId)));
		updateLikesNo.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		currentSession.close();
	}
}
