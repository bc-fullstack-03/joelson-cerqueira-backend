package com.casa.sysmap.services.authentication;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.casa.sysmap.models.User;
import com.casa.sysmap.services.UserService;
import com.casa.sysmap.services.security.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthenticateResponse authenticate(AuthenticateRequest request) {
		User user = userService.getUser(request.getEmail());
		
		if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new NoSuchElementException("Invalid user or password.");
		}
		
		String token = jwtService.generateToken(user.getId());
		var response = new AuthenticateResponse();
		response.setUserId(user.getId());
		response.setToken(token);
		return response;
	}

}
