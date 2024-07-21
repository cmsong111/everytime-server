package com.untouchable.everytime.User.Controller;

import com.untouchable.everytime.User.DTO.PasswordChangeRequest;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "2. User Service API", description = "유저 정보 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "User Info 조회", description = "JWT로 인증 후, 해당하는 유저 정보 조회")
    @ApiResponse(responseCode = "200", description = "유저 정보 가져오기 성공")
    public ResponseEntity<UserDTO> info(
            Principal principal) {
        return ResponseEntity.ok(userService.getUserInfo(principal.getName()));
    }

    @PatchMapping("/password")
    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정")
    public ResponseEntity<String> updatePassword(
            @RequestBody PasswordChangeRequest userChangePasswordDTO,
            Principal principal) {
        return ResponseEntity.ok(userService.updatePassword(principal.getName(), userChangePasswordDTO));
    }

    @PatchMapping("/email")
    @Operation(summary = "이메일 수정", description = "이메일 수정")
    public ResponseEntity<String> updateEmail(
            @RequestParam String email,
            Principal principal) {
        return ResponseEntity.ok(userService.updateEmail(principal.getName(), email));
    }

    @PatchMapping("/nickname")
    @Operation(summary = "닉네임 수정", description = "닉네임 수정")
    public ResponseEntity<String> updateNickname(
            @RequestParam String nickname,
            Principal principal) {
        return ResponseEntity.ok(userService.updateNickname(principal.getName(), nickname));
    }


    @DeleteMapping
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 하는 기능")
    public ResponseEntity<String> delete(
            Principal principal) {
        return ResponseEntity.ok(userService.deleteUser(principal.getName()));
    }
}
