package com.smartcareer.sercurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtSecurity {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private  Long expiration;

    private Key signingKey;

    @PostConstruct
    public void  setSigningKey()
    {
        signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    //generate JWT for the email
    public String generateJwTFromEmail(String email)
    {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(signingKey)
                .compact();

    }

    //Extract email from token
    public   String extractEmailFromJwt(String token)
    {
        Claims claim = Jwts.parser()
                .verifyWith((SecretKey) signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claim.getSubject();
    }

    //Check if the token has expired or not
    public boolean  isExpired(String token)
    {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey)signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration().before(new Date());

    }
    //Validate a token
    public boolean isTokenValid(String token)
    {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return !claims.getExpiration().before(new Date());

        } catch (Exception ex) {
            return false;
        }
    }




}
