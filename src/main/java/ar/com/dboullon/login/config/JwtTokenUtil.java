package ar.com.dboullon.login.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long serialVersionUID = -2550185165626007488L;

    private static final int JWT_TOKEN_VALIDITY = 37 * 60000;  // x * minutos en milis

    @Value("${jwt.secret}")
    private String secret;

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        } catch (SignatureException se) {
            logger.warn("SignatureException: Error algorithm type ");
            return null;
        } catch (Exception e) {
            logger.error("Error unknown: " + e.getMessage());
            return null;
        }
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) throws SignatureException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return ((username.equals(userDetails.getUsername())) && !isTokenExpired(token));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, String.valueOf(userDetails.getUsername()));
    }

    private String doGenerateToken(Map<String, Object> claims, String jti) {
        return Jwts.builder().setClaims(claims).setId(jti).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

}