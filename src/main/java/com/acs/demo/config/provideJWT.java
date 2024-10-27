package com.acs.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.Duration;

public class provideJWT {
    private static final SecretKey key = Keys.hmacShaKeyFor(ConfigConstants.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){

        Date expirationDate = new Date(System.currentTimeMillis() + Duration.ofHours(1).toMillis());

        return Jwts.builder()
                .setIssuer("acs-lessgo")
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }

}
