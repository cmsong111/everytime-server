package com.untouchable.everytime.user.controller

import com.untouchable.everytime.common.domain.AuthenticatedUser
import com.untouchable.everytime.config.SwaggerConfig.BEARER_AUTH
import com.untouchable.everytime.user.Service.UserService
import com.untouchable.everytime.user.Service.data.UserData
import com.untouchable.everytime.user.controller.data.PasswordChangeForm
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "2. User", description = "유저 정보 관련 API")
@RestController
@RequestMapping("/v1/api/user")
@SecurityRequirement(name = BEARER_AUTH)
class UserController(
    private val userService: UserService
) {

    @GetMapping
    @Operation(summary = "User Info 조회", description = "JWT로 인증 후, 해당하는 유저 정보 조회")
    fun getUserInfo(
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ): ResponseEntity<UserData> {
        return ResponseEntity.ok(
            userService.findUserById(authenticatedUser.userId)
        )
    }

    @PatchMapping("/nickname")
    @Operation(summary = "User Nickname 변경", description = "JWT로 인증 후, 해당하는 유저의 닉네임 변경")
    fun changeNickname(
        authenticatedUser: AuthenticatedUser,
        @RequestParam nickname: String
    ): ResponseEntity<UserData> {
        return ResponseEntity.ok(
            userService.changeNickname(authenticatedUser.userId, nickname)
        )
    }

    @PatchMapping("/email")
    @Operation(summary = "User Email 변경", description = "JWT로 인증 후, 해당하는 유저의 이메일 변경")
    fun changeEmail(
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
        @RequestParam email: String
    ): ResponseEntity<UserData> {
        return ResponseEntity.ok(
            userService.changeEmail(authenticatedUser.userId, email)
        )
    }

    @PatchMapping("/password")
    @Operation(summary = "User Password 변경", description = "JWT로 인증 후, 해당하는 유저의 비밀번호 변경")
    fun changePassword(
        authenticatedUser: AuthenticatedUser,
        @RequestBody passwordChangeForm: PasswordChangeForm,
    ): ResponseEntity<Unit> {
        userService.changePassword(authenticatedUser.userId, passwordChangeForm)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    @Operation(summary = "User 삭제", description = "JWT로 인증 후, 해당하는 유저 삭제")
    fun deleteUser(
        authenticatedUser: AuthenticatedUser
    ): ResponseEntity<Unit> {
        userService.deleteUser(authenticatedUser.userId)
        return ResponseEntity.ok().build()
    }
}
