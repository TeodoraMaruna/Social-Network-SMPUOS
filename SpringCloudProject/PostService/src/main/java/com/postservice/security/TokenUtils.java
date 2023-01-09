package com.postservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @Value("Authorization")
    private String AUTH_HEADER;


    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Boolean validateToken(String token){
        return (!isTokenExpired(token));
    }

    public String getUsernameFromToken(String token){
        String username;
        try {
            Claims claims = this.getAllClaimsFromToken(token);
            username = String.valueOf(claims.get("username"));
        }catch (Exception e){
            username = null;
        }

        return username;
    }

    public String getRoleFromToken(String token){
        String role;
        try{
            Claims claims = this.getAllClaimsFromToken(token);
            role = String.valueOf(claims.get("role"));
        }catch (Exception e){
            role = null;
        }
        return role;
    }


    public String getToken(HttpServletRequest request){
        String authHeader = getAuthHeaderFromHeader(request);

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request){
        return request.getHeader(AUTH_HEADER);
    }


    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        //return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
        return false;
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}
