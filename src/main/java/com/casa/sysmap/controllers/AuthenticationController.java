package com.casa.sysmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.sysmap.services.authentication.AuthenticateRequest;
import com.casa.sysmap.services.authentication.AuthenticateResponse;
import com.casa.sysmap.services.authentication.AuthenticationService;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping()
	public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request) {
		try {
			return ResponseEntity.ok().body(authenticationService.authenticate(request));
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}
