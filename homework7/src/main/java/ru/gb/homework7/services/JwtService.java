package ru.gb.homework7.services;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@Getter
public class JwtService {

    private final static SecretKey KEY = Jwts.SIG.HS512.key().build();
    private final String HEADER;
    private final int EXPIRATION_TIME;
    private final String TOKEN_PREFIX;

    public JwtService() {
        this.EXPIRATION_TIME = 300000;
        this.HEADER = "Authorization";
        this.TOKEN_PREFIX = "Bearer ";
    }

    public String createNewToken(UserDetails user) {
        String token = Jwts.builder()
                .subject(user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(JwtService.KEY)
                .compact();
        return TOKEN_PREFIX + token;
    }

    public String getTokenUsername(String token) {
        return Jwts.parser().verifyWith(JwtService.KEY).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isTokenValid(String token) {
        Date tokenDate = Jwts.parser().verifyWith(JwtService.KEY).build().parseSignedClaims(token).getPayload().getExpiration();
        return tokenDate.before(new Date());
    }



}
