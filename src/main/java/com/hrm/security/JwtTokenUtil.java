package com.hrm.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long jwtExpirationInMs;

	private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	// Retrieve username from JWT token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Retrieve expiration date from JWT token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// For retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	// Check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Generate token for user
	public String generateToken(String subject) {
		logger.debug("Generating token for {}", subject);
		return doGenerateToken(subject);
	}

	// Generates a JWT token with the given subject
	public String doGenerateToken(String subject) {
		logger.debug("Inside doGenerateToken with subject: {}", subject);
		Map<String, Object> claims = new HashMap<>();
		try {
			String token = Jwts.builder().setClaims(claims).setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs * 1000))
					.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();

			logger.debug("Token generated successfully: {}", token);
			return token;
		} catch (Exception e) {
			logger.error("Exception occurred in doGenerateToken: {}", e.getMessage(), e);
			throw e;
		}
	}

	// Generate a secure signing key
	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// Validate token
	public Boolean validateToken(String token, String username) {
		final String user = getUsernameFromToken(token);
		return (user.equals(username) && !isTokenExpired(token));
	}
}
