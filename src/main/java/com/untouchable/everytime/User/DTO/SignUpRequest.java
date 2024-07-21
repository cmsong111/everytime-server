package com.untouchable.everytime.User.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "회원가입 요청 DTO")
public class SignUpRequest {
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

    /**
     * 실명
     */
    @Schema(description = "이름(실명)", example = "test_name")
    String name;

    /**
     * 닉네임
     */
    @Schema(description = "닉네임", example = "test_nickname")
    String nickname;

    /**
     * 이메일
     */
    @Schema(description = "이메일", example = "test@test.com")
    String email;
}
