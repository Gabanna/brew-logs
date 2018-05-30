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

        Jws<Claims> jwt = Jwts.parser().setSigningKey(apiKey.getBytes()).parseClaimsJws(token.replace("Bearer ", ""));
        result = Optional.ofNullable(jwt.getBody());

        return result;
    }

    public String createJwt(User user) {
        long now = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, apiKey.getBytes())
                .setSubject(user.getUsername())
                .setIssuer(getClass().getSimpleName())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(now))
                .addClaims(user.toClaims());

        long exp;
        if(tokenExpiration != null && (exp = Long.valueOf(tokenExpiration)) > 0) {
            jwtBuilder.setExpiration(new Date(now + exp));
        }

        return jwtBuilder.compact();
    }

    public String createBearerToken(User user) {
        return BEARER_PREFIX + createJwt(user);
    }
}
