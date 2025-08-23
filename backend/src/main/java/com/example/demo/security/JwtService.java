package com.example.demo.security;

import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
import java.security.Key; import java.util.Date;

@Service
public class JwtService {
    private final Key key; private final long expirationMs;
    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long exp) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); this.expirationMs = exp;
    }
    public String generateToken(String username, String role){
        var now=new Date(); var exp=new Date(now.getTime()+expirationMs);
        return Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(now).setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }
    public String extractUsername(String token){ return parse(token).getSubject(); }
    public boolean isTokenValid(String token, String username){
        try { var c=parse(token); return c.getSubject().equals(username)&&c.getExpiration().after(new Date()); }
        catch(Exception e){ return false; }
    }
    private Claims parse(String t){ return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(t).getBody(); }
}
