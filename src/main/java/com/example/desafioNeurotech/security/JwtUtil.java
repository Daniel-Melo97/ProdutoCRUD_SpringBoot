package com.example.desafioNeurotech.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
     
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000;//token para expirar em um dia

    public static String generateToken(String username) {//gerador de token
        return Jwts.builder()
                .setSubject(username)//associa token ao usuário
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//expiração será dia e horário atual + 24 horas(em ms)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String extractUsername(String token) {//retorna o nome do usuário a partir do token
        return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validateToken(String token) {//valida um token, retorna true se válido, false caso contrário
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch(JwtException e) {
            return false;
        }
    }
}
