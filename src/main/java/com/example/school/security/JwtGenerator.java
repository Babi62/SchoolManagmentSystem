package com.example.school.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	public String tokenGenerator(Authentication auth) {
		String username = auth.getName();
		Date currentDate=new Date();
		Date jwtExpire= new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION );
		
		//new Date(System.currentTimeMillis() + 3600000) //1hour
		
		String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(jwtExpire)  // 1 min
                .signWith(SignatureAlgorithm.HS256, SecurityConstant.SECURITY_KEY)
                .compact();
		
		return token;
	}
	
	public String getUsernameFromJwt(String token) {
		Claims claim=Jwts.parser()
				.setSigningKey(SecurityConstant.SECURITY_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return claim.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(SecurityConstant.SECURITY_KEY)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("Session Expired or incorrect");
		}
	}
	
}
