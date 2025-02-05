package com.globallogic.test.user.config.security;

import com.globallogic.test.user.persistence.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public interface JwtUtil {
    String resolveToken(HttpServletRequest request);
    String createToken(User user);
    Claims resolveClaims(HttpServletRequest req);
    boolean validateClaims(Claims claims) throws AuthenticationException;
}

@Component
@Slf4j
@RequiredArgsConstructor
class JwtUtilImpl implements JwtUtil {

    @Value("${spring.security.secret-key}")
    private String secretKey;
    private static final long ACCESS_TOKEN_VALIDITY = 60L * 30L * 1000;
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";


    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("name", user.getName());
        Date tokenCreateTime = new Date();
        Date tokenValidity =
                new Date(tokenCreateTime.getTime() + TimeUnit.MILLISECONDS.toMillis(ACCESS_TOKEN_VALIDITY));
        log.info("Token validity:{}", tokenValidity);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            log.error("Error with expiration: {}", e.getMessage());
            throw e;
        }
    }

}
