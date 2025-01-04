package com.untouchable.everytime.common.domain

import com.untouchable.everytime.user.domain.UserRole
import java.time.Instant

/**
 * 인증된 사용자 정보 (JWT)
 * @property userId 사용자 ID
 * @property universityId 대학 ID
 * @property roles 사용자 역할
 * @property issuer 발급자
 * @property issuedAt 발급 시각
 * @property expiration 만료 시각
 */
data class AuthenticatedUser(
    val userId: String,
    val universityId: Long,
    val roles: Set<UserRole>,
    val issuer: String,
    val issuedAt: Instant,
    val expiration: Instant,
)
