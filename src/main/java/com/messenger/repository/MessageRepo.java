package com.messenger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.model.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {
	
	List<Message> findByAuthor(String author);
	
	Message findById(long id); 
	
	
	
}
