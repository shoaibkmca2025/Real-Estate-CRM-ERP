package com.bcs.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Issues and validates JWTs. Replaces the previous server-side
 * {@code HttpSession}/sessionStorage approach with stateless tokens suitable
 * for the React SPA.
 */
@Service
public class JwtService {

	private final SecretKey signingKey;
	private final long expirationMs;

	public JwtService(@Value("${app.jwt.secret}") String secret,
			@Value("${app.jwt.expiration-ms}") long expirationMs) {
		// Secret may be raw or Base64-encoded; fall back to raw bytes.
		byte[] keyBytes;
		try {
			keyBytes = Decoders.BASE64.decode(secret);
		} catch (Exception ex) {
			keyBytes = secret.getBytes();
		}
		this.signingKey = Keys.hmacShaKeyFor(keyBytes);
		this.expirationMs = expirationMs;
	}

	public String generateToken(int userId, String subject, Map<String, Object> claims) {
		Date now = new Date();
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.id(String.valueOf(userId))
				.issuedAt(now)
				.expiration(new Date(now.getTime() + expirationMs))
				.signWith(signingKey)
				.compact();
	}

	public String extractSubject(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public int extractUserId(String token) {
		return Integer.parseInt(extractClaim(token, Claims::getId));
	}

	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		return resolver.apply(parseClaims(token));
	}

	public boolean isValid(String token) {
		try {
			return parseClaims(token).getExpiration().after(new Date());
		} catch (Exception ex) {
			return false;
		}
	}

	private Claims parseClaims(String token) {
		return Jwts.parser()
				.verifyWith(signingKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
