package novares.uz.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwtSecretKey}")
    private String secretKey;

    @Value("${app.tokenLiveTime}")
    private Long tokenLiveTime;

    @Value("${app.refreshTokenLiveTime}")
    private Long refreshTokenLiveTime;

    public String generateAccessToken(Long userId) {
        return Jwts
                .builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSignInKey())
                .compact();
    }

    public String generateRefreshToken(Long userId) {
        return Jwts
                .builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenLiveTime))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token); // imzo + expiration tekshiriladi
            return true; // token to‘g‘ri
        } catch (Exception e) {
            return false; // noto‘g‘ri yoki muddati tugagan
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public long getAccessTokenExpirationSeconds() {
        return tokenLiveTime / 1000;
    }
}
