package de.rgse.brewlogs.services;

import de.rgse.brewlogs.domain.User;
import de.rgse.brewlogs.env.SystemEnv;
import de.rgse.brewlogs.env.SystemProperty;
import io.jsonwebtoken.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class JwtService {

    private static final String BEARER_PREFIX = "Bearer ";

    @Inject
    @SystemProperty(SystemEnv.BL_API_KEY)
    private String apiKey;

    @Inject
    @SystemProperty(SystemEnv.BL_TOKEN_EXPIRATION)
    private String tokenExpiration;

    public Optional<Claims> validateJwt(String token) {
        Optional<Claims> result;

        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token);
            result = Optional.ofNullable(jwt.getBody());

        } catch (JwtException e) {
            result = Optional.empty();
        }

        return result;
    }

    public String createJwt(User user) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, apiKey)
                .setSubject(user.getUsername())
                .setIssuer(getClass().getSimpleName())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + Long.valueOf(tokenExpiration)))
                .addClaims(user.toClaims())
                .compact();
    }

    public String createBearerToken(User user) {
        return BEARER_PREFIX + createJwt(user);
    }
}
