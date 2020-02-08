package com.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.model.Like;
import com.messenger.services.LikeService;

@RestController
@RequestMapping("api/message")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	@PostMapping("{messageId}/like")
	public void doLike(@RequestBody Like like, @PathVariable long messageId) {
		likeService.addLikeToMessage(like, messageId);
	}

}
