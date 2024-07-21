package com.untouchable.everytime.User.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "비밀번호 변경 요청 DTO")
public class PasswordChangeRequest {
    /**
     * 현재 비밀번호
     */
    @Schema(description = "현재 비밀번호", example = "old-password")
    private String currentPassword;

    /**
     * 새로운 비밀번호
     */
    @Schema(description = "새로운 비밀번호", example = "new-password")
    private String newPassword;

    /**
     * 새로운 비밀번호 확인
     */
    @Schema(description = "새로운 비밀번호 확인", example = "new-password")
    private String newPasswordConfirm;
}
