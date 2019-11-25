package com.messenger.controller;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.exceptions.IncorrectCredentialException;
import com.messenger.exceptions.RecordNotFoundException;
import com.messenger.model.AuthenticationRequest;
import com.messenger.model.AuthenticationResponse;
import com.messenger.services.UserDetailService;
import com.messenger.utilities.JwtUtil;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			 String  originalPassword = authenticationRequest.getPassword();
			 String generatedSecuredPasswordHash = Sha512DigestUtils.shaHex(originalPassword);
			

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), generatedSecuredPasswordHash));
		} catch (BadCredentialsException e) {
			
			throw new IncorrectCredentialException("The user credintials are wrong");
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
	
}
