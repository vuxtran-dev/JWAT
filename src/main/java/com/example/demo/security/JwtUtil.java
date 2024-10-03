
package com.example.demo.security;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${security.jwt.secret-key}")
    private String secretkey;



    public String generateToken(String username) {
        long now = System.currentTimeMillis(); 
        return Jwts.builder()
                .setSubject(username) 
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1000 * 60 * 60)) 
                .signWith(SignatureAlgorithm.HS256, secretkey) 
                .compact();
    }
    //  Get username from token
    public String extractUsername(String token) {
        Claims claims = parseToken(token);
        return claims != null ? claims.getSubject() : null;
    }
    
    // Check if the token has expired or not.
    public boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        return claims != null && claims.getExpiration().before(new Date());
    }
    
    // Check the validity of the token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (username.equals(extractedUsername) && !isTokenExpired(token));
    }

    private Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretkey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Expired token: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Token is not in the right format: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Token is unsupported: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Failed to parse token: {}", e.getMessage());
        }
        return null;
    }
}

