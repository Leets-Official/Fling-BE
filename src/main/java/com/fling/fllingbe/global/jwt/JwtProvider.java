package com.fling.fllingbe.global.jwt;

import com.fling.fllingbe.domain.user.dto.RefreshRequest;
import com.fling.fllingbe.global.error.exception.ServiceException;
import com.fling.fllingbe.global.jwt.exception.ExpiredTokenException;
import com.fling.fllingbe.global.jwt.exception.InvalidTokenException;
import com.fling.fllingbe.global.jwt.presentation.JwtResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.access_secret}")
    private String accessSecret;

    @Value("${jwt.refresh_secret}")
    private String refreshSecret;

    @Value("${spring.jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private long refreshExpirationTime;

    public String createAccessToken(String email) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationTime);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, accessSecret)
                .compact();
    }

    public String createRefreshToken(String email){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationTime);
        String refreshToken = Jwts.builder()
                .setSubject(email)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, refreshSecret)
                .compact();

        redisTemplate.opsForValue().set(
                email,
                refreshToken,
                refreshExpirationTime,
                TimeUnit.MILLISECONDS
        );

        return refreshToken;
    }

    public JwtResponse reissueToken(RefreshRequest refreshRequest) throws ServiceException {
        this.validateToken(refreshRequest.getRefreshToken(), true);
        Authentication authentication = this.getAuthentication(refreshRequest.getRefreshToken());
        if(!refreshRequest.getEmail().equals(authentication.getName()))
            throw new InvalidTokenException();

        String redisRefreshToken = redisTemplate.opsForValue().get(authentication.getName());

        if(!redisRefreshToken.equals(refreshRequest.getRefreshToken()))
            throw new InvalidTokenException();
        JwtResponse tokenDto = new JwtResponse(
                this.createAccessToken(authentication.getName()),
                "No Refresh Token Provided"
        );
        return tokenDto;
    }

    public Authentication getAuthentication(String token) {
        return new PreAuthenticatedAuthenticationToken(this.getEmail(token),null, null);
    }
    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(accessSecret).build().parseClaimsJws(token).getBody().getSubject();
    }
    public void validateToken(String token, boolean isRefreshToken) {
        try {
            parseClaims(token, isRefreshToken);
        } catch (SignatureException | UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e) {
            throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }

    public Claims parseClaims(String accessToken, boolean isRefreshToken) {
        try {
            JwtParser parser = Jwts.parser().setSigningKey(isRefreshToken ? refreshSecret : accessSecret);
            return parser.parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}