package com.untouchable.everytime.auth.controller

import com.untouchable.everytime.auth.controller.data.IdCheckForm
import com.untouchable.everytime.auth.controller.data.LoginForm
import com.untouchable.everytime.auth.controller.data.RegisterForm
import com.untouchable.everytime.auth.service.AuthService
import com.untouchable.everytime.auth.service.data.TokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "1. Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/v1/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/id-check")
    @Operation(summary = "아이디 중복 확인", description = "True: 이미 사용 중, False: 사용 가능")
    fun checkId(
        @RequestBody form: IdCheckForm
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok(authService.checkId(form.id))
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 성공 시 토큰 반환")
    fun login(
        @RequestBody form: LoginForm
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(authService.login(form))
    }

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입 성공 시 토큰 반환")
    fun register(
        @RequestBody form: RegisterForm
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(authService.register(form))
    }
}
