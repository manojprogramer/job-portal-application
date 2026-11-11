package com.manoj.job.job_portal_api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {
    private final SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractEmail(String token){
        return extractAllClaims(token).get("email",String.class);
    }
    public String extractAuthorities(String token){
        return extractAllClaims(token).get("authorities",String.class);
    }
    public String extractUserId(String token){
        return extractAllClaims(token).get("userId",String.class);
    }
    public Boolean isTokenValid(String token){
        try {
            extractAllClaims(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
