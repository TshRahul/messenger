package com.messenger.services;

import java.util.List;

import com.messenger.model.Message;

public interface MessageService {
	
	List<Message> getAllMessages();
	
	List<Message> getAllMessagesOfAuthor(String author);
	
	Message getMessageByID(long id);
	
	void addMessage(Message message);
	
	void updateMessage(Message message);
	
	String deleteMessage(long id);
 
}
