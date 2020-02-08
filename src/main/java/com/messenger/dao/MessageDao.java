package com.messenger.dao;

import java.util.List;

import com.messenger.model.Message;

public interface MessageDao {
	
	List<Message> get();
	
	List<Message> get(String username);
	
	Message get(long id);
	
	void save(Message message);
	
	void updateMessage(Message message);
	
	Message delete(long id);

}
