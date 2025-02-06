package com.movie_api.security;

import com.movie_api.entity.Token;
import com.movie_api.entity.User;
import com.movie_api.repository.TokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final String SECRET_KEY="#y0e4jo8p2ohe4iiioufsehx3t+j+2x2tiourgkjrb6b+";
    private final long EXPIRATION_TIME= 604800000; // 1 week

    private final TokenRepository tokenRepository;


    public String extractToken(HttpServletRequest request){
        final String header = request.getHeader("Authorization");
        return  header.substring(7);
    }
    public String generateToken(User user){
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getRole().name());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String jwt, UserDetails user) {
        try {
            String username = extractUserName(jwt);
            Claims claims = extractAllClaims(jwt);
            String role = claims.get("role", String.class);
            if (isTokenExpired(claims.getExpiration())) {
                return false; // Token is expired
            }
            if (!isTokenExist(jwt)) {
                return false;
            }
            // Validate the username and role
            boolean isUsernameValid = user.getUsername().equals(username);
            boolean isRoleValid = user.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(role));

            return isUsernameValid && isRoleValid;
        } catch (Exception e) {
            return false;
        }
    }
    public  boolean isTokenExist(String jwt){
        Optional<Token> token = tokenRepository.findByJwt(jwt);
        return token.isPresent();

    }
    public String extractUserName(String jwt){
        Claims claims = extractAllClaims(jwt);
        return claims.getSubject();
    }
    public String extractRole(String jwt){
        Claims claims = extractAllClaims(jwt);
        return claims.get("role",String.class);
    }
    private boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
    private Claims extractAllClaims(String jwt) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired", e);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}