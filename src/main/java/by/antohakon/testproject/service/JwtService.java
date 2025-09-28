package by.antohakon.testproject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 30; // 30 минут
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7 дней
    private static final String TOKEN_TYPE_CLAIM = "type";
    private static final String TOKEN_TYPE_ACCESS = "ACCESS";
    private static final String TOKEN_TYPE_REFRESH = "REFRESH";

    public String generateAccessToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_TYPE_CLAIM, TOKEN_TYPE_ACCESS);
        return createToken(claims, userName, ACCESS_TOKEN_EXPIRATION);
    }

    public String generateRefreshToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_TYPE_CLAIM, TOKEN_TYPE_REFRESH);
        return createToken(claims, userName, REFRESH_TOKEN_EXPIRATION);
    }

    public String generateToken(String userName) {
        return generateAccessToken(userName);
    }

    public Boolean validateRefreshToken(String token) {
        try {
            if (isTokenExpired(token)) {
                return false;
            }

            String tokenType = extractClaim(token, claims -> claims.get(TOKEN_TYPE_CLAIM, String.class));
            return TOKEN_TYPE_REFRESH.equals(tokenType);

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validateAccessToken(String token) {
        try {
            if (isTokenExpired(token)) {
                return false;
            }

            String tokenType = extractClaim(token, claims -> claims.get(TOKEN_TYPE_CLAIM, String.class));
            return TOKEN_TYPE_ACCESS.equals(tokenType);

        } catch (Exception e) {
            return false;
        }
    }

    private String createToken(Map<String, Object> claims, String userName, long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && validateAccessToken(token));
    }
}
