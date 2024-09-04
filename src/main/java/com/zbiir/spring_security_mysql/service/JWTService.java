package com.zbiir.spring_security_mysql.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class JWTService {
    @Value("${security.secretKey}")
    private String secretKey;



    public String getToken() {
        Map<String,Object> extraClaims = new HashMap<>();
        //extraClaims.put("USER","user1");

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject("USER")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
//        System.out.println(secretKey);
//        System.out.println(new String(Decoders.BASE64.decode(secretKey), StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(secretKey.getBytes());
        //byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        //System.out.println(keyBytes);
        //return Keys.hmacShaKeyFor(keyBytes);
    }


}
