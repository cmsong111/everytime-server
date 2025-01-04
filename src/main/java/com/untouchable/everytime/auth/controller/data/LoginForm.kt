package com.untouchable.everytime.auth.controller.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인 폼")
data class LoginForm(
    @Schema(description = "아이디", example = "user8")
    val id: String,
    @Schema(description = "비밀번호", example = "user")
    val password: String
)
