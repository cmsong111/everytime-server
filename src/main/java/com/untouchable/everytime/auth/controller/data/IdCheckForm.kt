package com.untouchable.everytime.auth.controller.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "아이디 중복 체크 폼")
data class IdCheckForm(
    @Schema(description = "아이디", example = "test")
    val id: String,
) {
}
