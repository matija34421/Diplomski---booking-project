package com.example.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String secret_key = "d6deaecb6a867bde2c2184d2a9ab662a554c647e902e6f1e58bc0a6aace4b043";
	
	public String generateToken(User user)
	{
		return generateToken(new HashMap<>(), user);
	}
	
	public String generateToken(Map<String, Object> claims,User user) {
		
		claims.put("roles", user.getAuthorities().stream()
		        .map(GrantedAuthority::getAuthority)
		        .collect(Collectors.toList())); // Add roles to claims
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 1000 * 60 * 24))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean isTokenValid(User user,String token) {
		final String email=extractEmail(token);
		boolean valid = (user.getEmail().equals(email) && !isTokenExpired(token));
	    System.out.println("Token valid: " + valid);
		return (user.getEmail().equals(email) && !isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public String extractPassword(String token) {
		return extractClaim(token, claims -> claims.get("password",String.class));
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private <T> T extractClaim(String token,Function<Claims,T>resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
