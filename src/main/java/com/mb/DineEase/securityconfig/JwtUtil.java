package com.mb.DineEase.securityconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.security.key}")
    private String secret_key;

    // Extract all claims from JWT token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secret_key).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(Claims claims) {
        return claims.getSubject();
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(Claims claims) {
        return claims.getExpiration();
    }

    // Check if the token has expired
    private Boolean isTokenExpired(Claims claims) {
        Date expirationDate = extractExpiration(claims);
        return expirationDate.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername(), userDetails.getAuthorities());
    }

    // Create a token with subject and expiration date
    private String createToken(String subject, Collection<? extends GrantedAuthority> authorities) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret_key));
        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours validity
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate the JWT token
    public boolean validateToken(Claims claims, UserDetails userDetails) {
        final String username = extractUsername(claims);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(claims));
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(Claims claims) {
        List<LinkedHashMap<String, String>> roles = (List<LinkedHashMap<String, String>>) claims.get("roles");
        return roles.stream()
                .map(roleMap -> new SimpleGrantedAuthority("ROLE_" + roleMap.get("authority")))
                .collect(Collectors.toList());
    }

}
