package com.untouchable.everytime.auth

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val issuer: String,
    val secretKey: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)
