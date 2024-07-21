package com.untouchable.everytime.User.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 로그인 요청 DTO
 */
@Data
@Schema(description = "로그인 요청 DTO")
public class LoginRequest {
    /**
     * 아이디
     */
    @Schema(description = "아이디", example = "test_id")
    String id;

    /**
     * 비밀번호
     */
    @Schema(description = "비밀번호", example = "test_password")
    String password;
}
