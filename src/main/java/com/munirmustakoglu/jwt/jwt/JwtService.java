package com.munirmustakoglu.jwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component // Component le işaretlendiğinde spring conteynırında bean i oluşur
public class JwtService {

    public static final String SECRET_KEY="vQPvXfAeN1BcAPc8eINYn1idnHoXVh8vIVNppZIxgZU=";

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("role", "ADMIN");
       return Jwts.builder()
               .setSubject(userDetails.getUsername())
               .setClaims(claimsMap)
               .setIssuedAt(new Date()) // sınıf ne zaman oluştu
               .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))  // şuanki zamanı milisaniye türüne çevirdim  sırasıyla  1 saniye ,dakika,saat,2 saat  tokennın geçerlilik süresi 2 saat
               .signWith(getKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    public <T> T exportToken(String token , Function<Claims,T> claimsFunction) {  // Toke ı çözmek için oluşturalan metot bu.

        Claims claims= Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        
        return claimsFunction.apply(claims);
    }

    public String getUserNameByToken(String token) {

        return exportToken(token, Claims::getSubject);  // explore token içerisindeki subject i çek

    }

    public boolean isTokenExpired(String token) {
      Date expireDate=  exportToken(token, Claims::getExpiration);

      return new Date().before(expireDate); // şuanki zaman benim tokenımın zamanından küçükse token geçerlidir aksi halde token geçersizdir
    }



    public Key getKey(){

      byte []keyBytes=  Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(keyBytes);
    }
}
