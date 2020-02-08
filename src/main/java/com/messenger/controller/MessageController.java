package com.messenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.model.Message;
import com.messenger.services.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
//	    @Autowired
//	  //  @Qualifier("like")
//	    LikeController like;
	
	
	@GetMapping("message")
	public List<Message> getAllMessages(){
		return messageService.getAllMessages();
	}
	
	@GetMapping("{author}/message")
	public List<Message> getMessagesForTheUser(@PathVariable String author){
		return messageService.getAllMessagesOfAuthor(author);
	}
	
	@GetMapping("message/{id}")
	public Message getMessageById(@PathVariable long id) {
		return messageService.getMessageByID(id);
			
		}
	@PostMapping("message")
	public Message postMessage(@RequestBody Message message) {
		messageService.addMessage(message);
		return message;
	}
	
	@PutMapping("message/{id}")
	public Message postMessage(@RequestBody Message message, @PathVariable long id) {
		message.setId(id);
		messageService.updateMessage(message);
		return message;
	}
	
	@DeleteMapping("message/{id}")
	public Message removeMessage(@PathVariable long id) {
		return messageService.deleteMessage(id);
	}
	
//	@RequestMapping("message/{id}/likes")
//	public LikeController getLikeController() {
//		return like;
//	}
	

}
