package com.untouchable.everytime.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * 토큰에서 사용자 이름을 추출한다.
     *
     * @param token 토큰
     * @return 사용자 이름
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 토큰에서 클레임을 추출한다.
     *
     * @param token          토큰
     * @param claimsResolver 클레임 추출 함수
     * @param <T>            클레임 타입
     * @return 클레임
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    /**
     * 토큰을 생성한다.
     *
     * @param extraClaims 추가 클레임
     * @param userDetails 사용자 정보
     * @param expiration  만료 시간
     * @return 토큰
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuer("everytime-clone")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 토큰이 유효한지 확인한다.
     *
     * @param token       토큰
     * @return 유효 여부
     */
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 토큰이 만료되었는지 확인한다.
     *
     * @param token 토큰
     * @return 만료 여부
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 토큰에서 만료 시간을 추출한다.
     *
     * @param token 토큰
     * @return 만료 시간
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 토큰에서 모든 클레임을 추출한다.
     *
     * @param token 토큰
     * @return 클레임
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

    }
}
