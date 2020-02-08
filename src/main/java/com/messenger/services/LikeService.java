package com.messenger.services;

import com.messenger.model.Like;

public interface LikeService {
	
	void addLikeToMessage(Like like, long messageId);

}
