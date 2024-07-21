package com.untouchable.everytime.User.Service;

import com.untouchable.everytime.User.DTO.AuthResponse;
import com.untouchable.everytime.User.DTO.LoginRequest;
import com.untouchable.everytime.User.DTO.SignUpRequest;
import com.untouchable.everytime.User.Entity.Role;
import com.untouchable.everytime.User.Entity.Token;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.TokenRepository;
import com.untouchable.everytime.User.Repository.UserRepository;
import com.untouchable.everytime.User.mapper.UserMapper;
import com.untouchable.everytime.common.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    /**
     * 로그인
     *
     * @param loginRequest 로그인 요청 DTO
     * @return AuthResponse
     */
    public AuthResponse login(LoginRequest loginRequest) {
        // 아이디로 사용자 조회
        User user = userRepository.findById(loginRequest.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // Refresh Token 저장
        Token token = tokenRepository.save(Token.builder()
                .refreshToken(jwtService.generateRefreshToken(user))
                .user(user)
                .build());

        // Access Token 및 Refresh Token 반환
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(token.getRefreshToken())
                .build();
    }

    /**
     * 회원가입
     *
     * @param signUpRequest 회원가입 요청 DTO
     * @return AuthResponse (AccessToken, RefreshToken)
     */
    public AuthResponse signup(SignUpRequest signUpRequest) {
        // ID 중복 확인
        if (userRepository.existsById(signUpRequest.getId())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        // User 생성 (Password 암호화, 일반 유저 권한 설정)
        User user = userMapper.toEntity(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setAuthorities(Set.of(Role.USER));
        User savedUser = userRepository.save(user);

        // Refresh Token 저장
        Token token = tokenRepository.save(Token.builder()
                .refreshToken(jwtService.generateRefreshToken(savedUser))
                .user(savedUser)
                .build());

        // Access Token 및 Refresh Token 반환
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(savedUser))
                .refreshToken(token.getRefreshToken())
                .build();
    }

    /**
     * ID 중복 체크
     *
     * @param userId 사용자 ID
     * @return Boolean (true: 사용 가능, false: 사용 불가능)
     */
    public Boolean userDuplicationCheck(String userId) {
        return !userRepository.existsById(userId);
    }

    /**
     * Refresh Token 을 이용한 Access Token 재발급
     *
     * @param refreshToken Refresh Token
     * @return AuthResponse (AccessToken)
     */
    public AuthResponse refreshToken(String refreshToken) {
        // Refresh Token 검증
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 Refresh Token 입니다.");
        }

        // DataBase에 Refresh Token이 존재하는지 확인
        if (!tokenRepository.existsByRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("존재하지 않는 Refresh Token 입니다.");
        }

        // Access Token 재발급
        String username = jwtService.extractUsername(refreshToken);
        User user = userRepository.findById(username).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
        );

        // Access Token 반환
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(null)
                .build();
    }

    /**
     * 로그아웃
     *
     * @param username 사용자 ID
     */
    @Transactional
    public void logout(String username) {
        tokenRepository.deleteAllByUserId(username);
    }
}
