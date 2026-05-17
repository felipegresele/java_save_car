package com.saveCar.SaveCar.infra.security;

import com.saveCar.SaveCar.infra.exceptions.InvalidTokenAcessExpection;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {

    //Pega o valor que ta no app properties
    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.key}")
    private String key;

    public String gerarToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return buildToken(user.getUsername());
    }

    public String buildToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username) //Guardar info do user dentro do token
                .issuedAt(now) //Quando foi gerado
                .expiration(expiration) //Data de expiração
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        //hmacShaKeyFor - Algoritmo usado para gerar a chave
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    //Vaidar token
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        }catch(JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //Claim é a estrutura do Token
    private Claims getClaims(String token) {
        // validar assinatura
        // validar expiracao
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }
}
