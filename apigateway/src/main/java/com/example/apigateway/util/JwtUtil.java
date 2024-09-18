package com.example.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	//Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	public void validateToken(final String token) {
		System.out.println("token in validateToken function before " + token);
		// Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
		System.out.println("Token is valid");
	}

	private byte[] getSignKey() {
	//	Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
		 byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return keyBytes;

		// return

		// return keys.
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
}
