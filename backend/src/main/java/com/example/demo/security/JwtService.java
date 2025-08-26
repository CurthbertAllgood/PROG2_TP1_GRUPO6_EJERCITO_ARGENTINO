package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final Key key;
    private final long expirationMs;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long exp
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = exp;
    }

    /** Nuevo: personaId incluido en el JWT */
    public String generateToken(String username, String role, Long personaId) {
        var now = new Date();
        var exp = new Date(now.getTime() + expirationMs);

        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256);

        if (personaId != null) {
            builder.claim("personaId", personaId);
        }
        return builder.compact();
    }

    /** Compatibilidad si en algún punto lo seguías llamando sin personaId */
    @Deprecated
    public String generateToken(String username, String role) {
        return generateToken(username, role, null);
    }

    public String extractUsername(String token) {
        return parse(token).getSubject();
    }

    /** Útil si querés consultar el rol desde el filtro u otros lugares */
    public String extractRole(String token) {
        Object r = parse(token).get("role");
        return (r != null) ? r.toString() : null;
    }

    /** Útil si querés leer personaId en el backend */
    public Long extractPersonaId(String token) {
        Object p = parse(token).get("personaId");
        if (p == null) return null;
        if (p instanceof Long) return (Long) p;
        if (p instanceof Integer) return ((Integer) p).longValue();
        return Long.parseLong(p.toString());
    }

    public boolean isTokenValid(String token, String username) {
        try {
            var c = parse(token);
            return c.getSubject().equals(username) && c.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parse(String t) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(t)
                .getBody();
    }
}
