package com.messenger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	
	Optional<User> findByUsername(String username);

}
