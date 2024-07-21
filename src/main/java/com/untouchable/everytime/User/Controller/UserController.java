package com.untouchable.everytime.User.Controller;

import com.untouchable.everytime.User.DTO.UserChangePasswordDTO;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.DTO.UserLoginDTO;
import com.untouchable.everytime.User.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자", description = "유저 정보 CRUD 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "ID, PWD를 입력받아 JWT 발급하는 기능")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userDTO) {
        return userService.login(userDTO.getUserId(), userDTO.getUserPwd());
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "유저 정보를 입력받아 회원가입 하는 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "ID가 중복입니다."),
            @ApiResponse(responseCode = "200", description = "회원가입 성공")
    })
    public ResponseEntity<UserDTO> singup(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PatchMapping("/update")
    @Operation(summary = "회원 정보 수정", description = "유저 정보를 입력받아 수정하는 기능, 현재 닉네임, 이메일만 변경 가능")
    public ResponseEntity<UserDTO> update(
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token,
            @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO, token);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 하는 기능")
    public ResponseEntity<String> delete(
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return userService.deleteUser(token);
    }

    @Operation(summary = "User Info 조회", description = "JWT로 인증 후, 해당하는 유저 정보 조회")
    @ApiResponse(responseCode = "200", description = "유저 정보 가져오기 성공")
    @GetMapping("/info")
    public ResponseEntity<UserDTO> info(
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return userService.getUserByToken(token);
    }

    @Operation(summary = "ID 중복체크", description = "ID 중복체크")
    @ApiResponse(responseCode = "200", description = "true -> ID 사용가능, false -> 사용 불가능")
    @PostMapping("/Id-check")
    public ResponseEntity<Boolean> checkId(
            @Parameter(name = "userId", description = "유저 ID") @RequestParam(value = "userId") String userId) {
        return userService.userDuplicationCheck(userId);
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정")
    public ResponseEntity<String> UserPasswordModify(
            @RequestBody UserChangePasswordDTO userChangePasswordDTO,
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return userService.modifyUserPassword(userChangePasswordDTO, token);
    }
}
