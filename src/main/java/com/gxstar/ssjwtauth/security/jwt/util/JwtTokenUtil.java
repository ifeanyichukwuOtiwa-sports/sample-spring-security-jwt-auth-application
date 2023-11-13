package com.gxstar.ssjwtauth.security.jwt.util;

import com.gxstar.ssjwtauth.model.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    @Value("${app.jwt.secret:YWJjZGVmZ2hpamtsbW5vcFFSU1RVVldYWVowMTIzNDU2Nzg5}")
    private String jwtSecretKey;
    @Value("${app.jwt.expiration:86400000}")
    private long jwtTokenExpiresIn;


    public String generateAccessToken(final User u) {

        final SecretKey secretKey = getSecretKeyFromEncodeSecret();
        return Jwts.builder()
                .subject("%s:%s:%s:%s".formatted(u.getId(), u.getName(), u.getEmail(), u.getUsername()))
                .issuer("gxstar007")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(jwtTokenExpiresIn)))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public boolean validateAccessToken(final String token) {
        final SecretKey key = getSecretKeyFromEncodeSecret();
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (final JwtException e) {
            log.error("Error parsing", e);
            throw new RuntimeException(e);
        }
    }

    private SecretKey getSecretKeyFromEncodeSecret() {
        final byte[] bytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getSubject(final String token) {
        return Jwts.parser()
                .verifyWith(getSecretKeyFromEncodeSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
