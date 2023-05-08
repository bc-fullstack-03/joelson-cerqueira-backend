package com.casa.sysmap.services.authentication;

import lombok.Data;

@Data
public class AuthenticateRequest {

	private String email;
	private String password;
	
}
