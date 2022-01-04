package com.medical.security;

import com.medical.config.AppProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final AppProperties appProperties;

    public TokenProvider(final AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(final Authentication authentication) {
        final UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + this.appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, this.appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(this.appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(this.appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (final SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (final MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (final ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (final UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (final IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
