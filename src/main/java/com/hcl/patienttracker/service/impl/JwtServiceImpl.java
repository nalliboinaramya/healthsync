package com.hcl.patienttracker.service.impl;

import com.hcl.patienttracker.entity.Admin;
import com.hcl.patienttracker.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${spring.secret-key}")
    private String secretKey;
//this below line is for secret key file from the properties and encrypt it
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
//token creation
    @Override
    public String generateToken(Admin admin) {
        return Jwts.builder()
                .subject(admin.getId().toString())
                .claim("firstname", admin.getFirstName())
                .claim("LastName", admin.getLastName())
                .claim("email", admin.getEmail())
                .claim("role", admin.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//1 hr
                .signWith(getSecretKey())// .signWith(getSecretKey(),HS512) id default Algo
                .compact();
    }
// the below method is for verification the token
    @Override
    public Long getAdminIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String subject = claims.getSubject();
        return Long.valueOf(subject);
    }

}
