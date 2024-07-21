package com.untouchable.everytime.User.Controller;

import com.untouchable.everytime.User.DTO.AuthResponse;
import com.untouchable.everytime.User.DTO.LoginRequest;
import com.untouchable.everytime.User.DTO.SignUpRequest;
import com.untouchable.everytime.User.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "1. Authentication API", description = "로그인, 회원 가입, 토큰 재발급 API (Authentication Header Should be Empty)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    /**
     * AuthService
     */
    private final AuthService authService;

    /**
     * 로그인 API
     *
     * @param loginRequest 로그인 요청 DTO
     * @return ResponseEntity<AuthResponse>
     */
    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "ID, Password 이용한 로그인 API<br>성공 시 AccessToken, RefreshToken 반환")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    /**
     * 회원가입 API
     *
     * @param signUpRequest 회원가입 요청 DTO
     * @return ResponseEntity<AuthResponse>
     */
    @PostMapping("/signup")
    @Operation(summary = "회원가입 API", description = "ID, Password, Name, Email 이용한 회원가입 API<br>성공 시 AccessToken, RefreshToken 반환")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody SignUpRequest signUpRequest
    ) {
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }

    /**
     * ID 중복체크 API
     *
     * @param userId 사용자 ID
     * @return ResponseEntity<Boolean>
     */
    @PostMapping("/checkId")
    @Operation(summary = "ID 중복체크 API", description = "사용자 ID 중복 체크 API<br>중복 시 True, 중복이 아닐 시 False 반환")
    public ResponseEntity<Boolean> checkId(
            @RequestParam(value = "userId") String userId
    ) {
        return ResponseEntity.ok(authService.userDuplicationCheck(userId));
    }


    /**
     * 토큰 재발급 API
     *
     * @param refreshToken Refresh Token
     * @return ResponseEntity<AuthResponse> (AccessToken Only)
     */
    @PostMapping("/refresh-token")
    @Operation(summary = "토큰 재발급 API", description = "Refresh Token을 이용한 AccessToken 재발급 API")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestHeader("RefreshToken") @Schema(description = "Refresh Token") String refreshToken
    ) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    /**
     * 로그아웃 API
     *
     * @param principal Principal
     * @return ResponseEntity<String>
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    @Operation(summary = "로그아웃 API", description = "로그아웃 API<br>저장된 Refresh Token들을 모두 삭제합니다 (모든 기기 로그아웃)")
    public ResponseEntity<String> logout(
            Principal principal
    ) {
        authService.logout(principal.getName());
        return ResponseEntity.ok("로그아웃 성공");
    }
}
