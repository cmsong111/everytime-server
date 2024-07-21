package com.untouchable.everytime.User.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Auth Response DTO
 */
@Data
@Builder
@Schema(description = "인증 응답 DTO")
public class AuthResponse {
    /**
     * 액세스 토큰
     */
    @Schema(description = "액세스 토큰")
    private String accessToken;

    /**
     * 리프레시 토큰
     */
    @Schema(description = "리프레시 토큰")
    @JsonInclude(JsonInclude.Include.NON_NULL) // 리프레시 토큰은 로그인, 회원가입 시 반환하지 않음
    private String refreshToken;
}
