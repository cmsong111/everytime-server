package com.untouchable.everytime.common.domain

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleDomainException(e: NotFoundException): ResponseEntity<Any?> {
        log.debug { "DomainException: ${e.code} ${e.message}" }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            mapOf(
                "code" to e.code,
                "message" to e.message
            )
        )
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleDomainException(e: BadRequestException): ResponseEntity<Any?> {
        log.debug { "DomainException: ${e.code} ${e.message}" }
        return ResponseEntity.badRequest().body(
            mapOf(
                "code" to e.code,
                "message" to e.message
            )
        )
    }

    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleDomainException(e: UnauthorizedException): ResponseEntity<Any?> {
        log.debug { "DomainException: ${e.code} ${e.message}" }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            mapOf(
                "code" to e.code,
                "message" to e.message
            )
        )
    }

    @ExceptionHandler(ForbiddenException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleDomainException(e: ForbiddenException): ResponseEntity<Any?> {
        log.debug { "DomainException: ${e.code} ${e.message}" }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            mapOf(
                "code" to e.code,
                "message" to e.message
            )
        )
    }

    @ExceptionHandler(ConflictException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleDomainException(e: ConflictException): ResponseEntity<Any?> {
        log.debug { "DomainException: ${e.code} ${e.message}" }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            mapOf(
                "code" to e.code,
                "message" to e.message
            )
        )
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
