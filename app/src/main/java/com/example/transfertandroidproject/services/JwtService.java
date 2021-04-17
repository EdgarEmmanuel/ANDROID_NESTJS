package com.example.transfertandroidproject.services;

import android.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JwtService {

    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   // static Key secret = MacProvider.generateKey();

    private static String stringKey = "futurize";
    private static byte[] encodedKey = Base64.decode(stringKey,3);
    private static SecretKey t = new SecretKeySpec(encodedKey, 0, encodedKey.length,
            "HmacSH256");

    public static Date addHoursToJavaUtilDate( int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }


    public static String encodeToken(Object infos){
        String jws = Jwts.builder()
                .setSubject("Joe")
                .claim("user",infos)
                .setExpiration(addHoursToJavaUtilDate(3)) //a java.util.Date
                .signWith(key).compact();

        return jws;
    }

    public static Boolean verifyToken(String token){
            try {

                decodeToken(token);

                return true;
                // we can safely trust the JWT
            } catch (JwtException ex) {       // (5)
                return false;
            }

    }


    public static Jws<Claims> decodeToken(String token){

       Jws<Claims> jws = Jwts.parserBuilder()  // (1)
                .setSigningKey(t)
               .build()//(2)// (3)
                .parseClaimsJws(token);

        System.out.println("============================================");
        System.out.println("Token Decoded =>"+jws.getBody());

       return jws;

    }

}
