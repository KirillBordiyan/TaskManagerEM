package effective_mobile.tsm.security;

import effective_mobile.tsm.config.JwtProperties;
import effective_mobile.tsm.exceptions.model.AccessDeniedException;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.security.body.JwtResponse;
import effective_mobile.tsm.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties properties;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(properties.getSecret());
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());
        claims.put("authorities", List.of(user.getRole()));

        Instant val = Instant.now()
                .plus(properties.getAccess(), ChronoUnit.HOURS);

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUserId().toString())
                .expiration(Date.from(val))
                .signWith(generateKey())
                .compact();
    }

    public String generateRefreshTokenToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("username", user.getEmail());

        Instant val = Instant.now()
                .plus(properties.getRefresh(), ChronoUnit.DAYS);

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUserId().toString())
                .expiration(Date.from(val))
                .signWith(generateKey())
                .compact();
    }

    public JwtResponse refreshUserToken(String refresh){
        JwtResponse response = new JwtResponse();
        if(!isTokenValid(refresh)){
            throw new AccessDeniedException();
        }
        String email = extractUserEmail(refresh);
        User user = userService.getEntityByEmail(email);

        response.setUserId(user.getUserId());
        response.setEmail(email);

        response.setAccess(generateAccessToken(user));
        response.setRefresh(generateAccessToken(user));

        return response;
    }

    public String extractUserEmail(String token){
        Claims cl = getClaims(token);
        return String.valueOf (cl.get("email"));
    }

    public UUID extractUserId(String jwt) {
        Claims claims = getClaims(jwt);
        User user = userService.getEntityById(UUID.fromString(claims.getSubject()));
        return user.getUserId();
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

    public Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }


    public Authentication getAuthentication(String jwt) {
        String userId = extractUserId(jwt).toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
