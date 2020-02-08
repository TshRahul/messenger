package com.messenger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.messenger.dao.MessageDao;
import com.messenger.model.Message;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDao messageDao;

	@Override
	@Transactional
	public List<Message> getAllMessages() {
		return messageDao.get();
	}

	@Override
	@Transactional
	public List<Message> getAllMessagesOfAuthor(String author) {
		return messageDao.get(author);
	}

	@Override
	@Transactional
	public Message getMessageByID(long id) {
		return messageDao.get(id);
	}

	@Override
	@Transactional
	public void addMessage(Message message) {
		messageDao.save(message);
	}

	@Override
	@Transactional
	public void updateMessage(Message message) {
		messageDao.updateMessage(message);
	}

	@Override
	@Transactional
	public Message deleteMessage(long id) {
		return messageDao.delete(id);
	}
	
	

}
