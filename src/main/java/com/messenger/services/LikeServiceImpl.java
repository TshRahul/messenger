package com.messenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.messenger.dao.LikeDao;
import com.messenger.model.Like;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	LikeDao likeDao;

	@Override
	@Transactional
	public void addLikeToMessage(Like like, long messageId) {
		likeDao.save(like, messageId);
	}

}
