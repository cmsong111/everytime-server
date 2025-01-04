package com.untouchable.everytime.common.domain

sealed class DomainException(
    val code: String,
    override val message: String,
) : RuntimeException(message)

sealed class NotFoundException(
    code: String,
    message: String,
) : DomainException(code, message)

sealed class BadRequestException(
    code: String,
    message: String,
) : DomainException(code, message)

sealed class ForbiddenException(
    code: String,
    message: String,
) : DomainException(code, message)

sealed class UnauthorizedException(
    code: String,
    message: String,
) : DomainException(code, message)

sealed class ConflictException(
    code: String,
    message: String,
) : DomainException(code, message)


data class UnauthorizedAccessException(
    override val message: String = "인증되지 않은 접근입니다.",
) : UnauthorizedException("UNAUTHORIZED", message)

data class InvalidTokenException(
    override val message: String = "유효하지 않은 토큰입니다.",
) : UnauthorizedException("INVALID_TOKEN", message)


data class UserNotFoundException(
    override val message: String = "존재하지 않는 유저입니다.",
) : NotFoundException("USER_NOT_FOUND", message)

data class UserConflictException(
    override val message: String = "이미 존재하는 유저입니다.",
) : ConflictException("USER_CONFLICT", message)
