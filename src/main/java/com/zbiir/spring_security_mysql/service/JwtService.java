package com.zbiir.spring_security_mysql.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Supplier;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class JwtService {
    @Value("${security.secretKey}")
    private String secretKey;


    public String getToken(String username) {
        Map<String, Object> extraClaims = new HashMap<>();
        //extraClaims.put("USER","user1");

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        //byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(secretKey.getBytes());

    }


    public String extractSubject(String token) {
        //return extractClaim(token,Claims::getSubject);
        return verifyTokenExtractClaims(token).getSubject();
    }

    private <T> T extractClaim(String token, Function <Claims,T> claimResolver){
        Claims claims = verifyTokenExtractClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims verifyTokenExtractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token) //parsing token Exeptions should be handled
                .getPayload();

    }
}
