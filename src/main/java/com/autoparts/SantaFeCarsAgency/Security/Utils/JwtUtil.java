package com.autoparts.SantaFeCarsAgency.Security.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Map;

@Getter
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public  String generateToken(Map<String,String> claims){
        byte[] decodedKey = Base64.getDecoder().decode(getSecretKey());
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public Jws<Claims> validateToken(String JwtToken){
        try{
            return  Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(JwtToken);
        }
        //TODO: ADD JWTID EXCEPTION
        catch (Exception e){
            return  null;
        }
    }
}
