package com.casa.sysmap.services.security;

import java.util.UUID;

public interface JwtService {

	String generateToken(UUID userId);

	boolean isValidToken(String token, String userId);
}
