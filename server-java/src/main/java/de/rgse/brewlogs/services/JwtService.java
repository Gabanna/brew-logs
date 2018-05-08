package de.rgse.brewlogs.services;

import de.rgse.brewlogs.env.SystemEnv;
import de.rgse.brewlogs.env.SystemProperty;
import io.jsonwebtoken.*;

import javax.inject.Inject;
import java.util.Optional;

public class JwtService {

    @Inject
    @SystemProperty(SystemEnv.BL_API_KEY)
    private String apiKey;

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
}
