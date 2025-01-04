package com.untouchable.everytime.auth

import com.untouchable.everytime.common.domain.AuthenticatedUser
import com.untouchable.everytime.common.domain.InvalidTokenException
import com.untouchable.everytime.user.domain.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders.BASE64
import io.jsonwebtoken.security.Keys
import java.time.Instant
import java.util.Date
import java.util.UUID
import javax.crypto.SecretKey
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component


@Component
@EnableConfigurationProperties(JwtProperties::class)
class TokenProvider(
    private val jwtProperties: JwtProperties
) {
    /**
     * JWT 키
     */
    private val key: SecretKey = Keys.hmacShaKeyFor(BASE64.decode(jwtProperties.secretKey))


    /**
     * JWT 토큰 생성
     */
    fun createToken(
        userId: String,
        universityId: Long,
        roles: Set<UserRole>,
        expire: Long = jwtProperties.accessTokenExpiration
    ): String {
        return Jwts.builder()
            .id(UUID.randomUUID().toString())
            .subject(userId)
            .claim("universityId", universityId)
            .claim("roles", roles)
            .issuer(jwtProperties.issuer)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plusMillis(expire * 1000)))
            .signWith(key)
            .compact()
    }

    /**
     * JWT 토큰에서 정보 추출
     */
    fun decodeToken(token: String): AuthenticatedUser {
        try {
            val claims: Claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload

            return AuthenticatedUser(
                userId = claims.subject,
                universityId = claims["universityId", Integer::class.java]!!.toLong(),
                roles = claims["roles", Collection::class.java]!!.map { UserRole.valueOf(it as String) }.toSet(),
                issuer = claims.issuer,
                issuedAt = claims.issuedAt.toInstant(),
                expiration = claims.expiration.toInstant()
            )
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }
}
