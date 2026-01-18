package com.ecommerce.userservice.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // ONE secure key for entire application
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 🔹 Generate JWT Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                ) // 1 hour
                .signWith(key)
                .compact();
    }

    // 🔹 Extract username (email) from token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
