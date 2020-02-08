package com.messenger.dao;

import com.messenger.model.Like;

public interface LikeDao {
	
	void save(Like like, long messageId);

}
