package com.untouchable.everytime.auth.controller.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원가입 폼")
class RegisterForm(
    @field:Schema(description = "아이디")
    var id: String,
    @field:Schema(description = "비밀번호")
    var password: String,
    @field:Schema(description = "비밀번호 확인")
    var passwordConfirm: String,
    @field:Schema(description = "이름")
    var name: String,
    @field:Schema(description = "닉네임")
    var nickname: String,
    @field:Schema(description = "이메일")
    var email: String,
    @field:Schema(description = "학교 아이디", example = "1")
    var universityId: Long,
    @field:Schema(description = "입학년도", example = "2021")
    var entranceYear: Int,
) {
}
