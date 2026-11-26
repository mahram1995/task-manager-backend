
package com.example.dailytask.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final SecretKey key;
    private final long ttl;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long ttl) {

        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException(
                    "JWT secret must not be empty"
            );
        }

        if (ttl <= 0) {
            throw new IllegalArgumentException(
                    "JWT expiration must be greater than zero"
            );
        }

        try {
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            this.key = Keys.hmacShaKeyFor(decodedKey);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid JWT secret. Use a Base64-encoded key "
                            + "of at least 32 bytes.", e
            );
        }

        this.ttl = ttl;
    }
    public boolean isTokenValid(String token) {
        return valid(token);
    }

    // Generate JWT token
    public String generate(String username) {

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(now))
                .expiration(new Date(now + ttl))
                .signWith(key)
                .compact();
    }

    // Extract username
    public String username(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Validate JWT token
    public boolean valid(String token) {

        try {
            username(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
