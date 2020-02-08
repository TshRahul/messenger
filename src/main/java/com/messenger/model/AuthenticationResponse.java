package com.messenger.model;

public class AuthenticationResponse {
	
	private String jwt;
	private User user;

	public AuthenticationResponse(String jwt, User user) {
		super();
		this.jwt = jwt;
		this.user = user;
	}
	
	public String getJwt() {
		return jwt;
	}
	
	public User getUser() {
		return user;
	}
	

}
