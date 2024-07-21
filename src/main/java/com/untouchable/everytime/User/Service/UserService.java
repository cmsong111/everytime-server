package com.untouchable.everytime.User.Service;

import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.DTO.PasswordChangeRequest;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import com.untouchable.everytime.User.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final BoardRepository boardRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    /**
     * 유저 정보 조회
     *
     * @param userName 유저 아이디
     * @return User 정보
     */
    public UserDTO getUserInfo(String userName) {
        User user = userRepository.findById(userName).orElseThrow();
        return userMapper.toDTO(user);
    }


    /**
     * 비밀번호 변경
     *
     * @param userName              유저 아이디
     * @param userChangePasswordDTO 비밀번호 변경 DTO
     * @return 변경 완료
     */
    @Transactional
    public String updatePassword(String userName, PasswordChangeRequest userChangePasswordDTO) {
        User user = userRepository.findById(userName).orElseThrow();

        // 현재 비밀번호가 일치하지 않을 경우
        if (!encoder.matches(userChangePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호 2번째랑 일치 하지 않을 경우
        if (!userChangePasswordDTO.getNewPassword().equals(userChangePasswordDTO.getNewPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(encoder.encode(userChangePasswordDTO.getNewPassword()));
        userRepository.save(user);
        return "비밀번호 변경 완료";
    }

    /**
     * 이메일 변경
     *
     * @param userName 유저 아이디
     * @param email    변경할 이메일
     * @return 변경 완료
     */
    @Transactional
    public String updateEmail(String userName, String email) {
        User user = userRepository.findById(userName).orElseThrow();

        user.setEmail(email);
        userRepository.save(user);
        return "이메일 변경 완료";
    }

    /**
     * 닉네임 변경
     *
     * @param userName 유저 아이디
     * @param nickname 변경할 닉네임
     * @return 변경 완료
     */
    @Transactional
    public String updateNickname(String userName, String nickname) {
        User user = userRepository.findById(userName).orElseThrow();

        user.setNickname(nickname);
        userRepository.save(user);
        return "닉네임 변경 완료";
    }

    /**
     * 유저 삭제
     * @param userName 유저 아이디
     * @return 삭제 완료
     */
    @Transactional
    public String deleteUser(String userName) {
        userRepository.deleteById(userName);
        return "유저 삭제 완료";
    }
}
