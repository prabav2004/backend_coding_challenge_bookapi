package com.example.bookapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Must be at least 32 characters
    private static final String SECRET = "mysecretkeymysecretkeymysecretkey12";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 🔐 Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                         // set username
                .setIssuedAt(new Date())                      // current time
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getSigningKey())                    // sign with key
                .compact();
    }

    // 🔍 Extract Username from Token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 🔍 Extract Claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}