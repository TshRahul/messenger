package com.messenger.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.messenger.model.MyUserDetails;
import com.messenger.model.User;
import com.messenger.repository.UserRepo;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepo.findByUsername(username);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found : " + username));
		return user.map(MyUserDetails::new).get();
		
	}
	
	

}
