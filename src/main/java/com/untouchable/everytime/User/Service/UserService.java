package com.untouchable.everytime.User.Service;

import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.DTO.UserChangePasswordDTO;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    SchoolRepository schoolRepository;
    BoardRepository boardRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;
    PasswordEncoder encoder;


    public ResponseEntity<String> login(String id, String pwd) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (encoder.matches(pwd, user.get().getUserPwd())) {
            return ResponseEntity.ok(jwtConfig.createToken(user.get()));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<UserDTO> register(UserDTO userDTO) {
        // id 중복 확인
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            return ResponseEntity.badRequest().build();
        }
        // 학교 존재 여부 확인
        Optional<School> school = schoolRepository.findById(userDTO.getUserSchoolSchoolName());
        if (school.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // 유저 생성 후 저장
        User user = modelMapper.map(userDTO, User.class);
        user.setUserPwd(encoder.encode(user.getUserPwd()));
        user.setUserPoint(0L);
        user.setUserSchool(school.get());
        user = userRepository.save(user);

        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, String token) {
        Map<String, Object> result = jwtConfig.verifyJWT(token);
        User newUserInfo = modelMapper.map(userDTO, User.class);
        User userInfo = userRepository.findById((String) result.get("userId")).get();

        //이메일 변경
        if (!newUserInfo.getUserEmail().isEmpty() || !userInfo.getUserEmail().equals(newUserInfo.getUserEmail())) {
            userInfo.setUserEmail(newUserInfo.getUserEmail());
        }

        // 닉네임 변경
        if (!newUserInfo.getUserNickname().isEmpty() || !userInfo.getUserNickname().equals(newUserInfo.getUserNickname())) {
            userInfo.setUserNickname(newUserInfo.getUserNickname());
        }

        userInfo = userRepository.save(userInfo);
        return ResponseEntity.ok(modelMapper.map(userInfo, UserDTO.class));
    }

    public ResponseEntity<String> deleteUser(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<User> user = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 유저가 작성한 게시글 NULL 처리
        boardRepository.updateUserByUser(null,user.get());

        // 댓글
        //Todo : 댓글,좋아요 히스토리 등등 삭제

        userRepository.deleteById(String.valueOf(jwt.get("userId")));
        return ResponseEntity.ok("유저 삭제 완료");
    }

    public ResponseEntity<UserDTO> getUserByToken(String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        Optional<User> user = userRepository.findById(String.valueOf(jwt.get("userId")));

        if (user.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(user.get(), UserDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Boolean> userDuplicationCheck(String userId) {
        return ResponseEntity.ok(!userRepository.existsByUserId(userId));
    }

    public ResponseEntity<String> modifyUserPassword(UserChangePasswordDTO userChangePasswordDTO, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        User user = userRepository.findById(String.valueOf(jwt.get("userId"))).get();

        // 현재 비밀번호가 일치하지 않을 경우
        if (!encoder.matches(userChangePasswordDTO.getUserPwd(), user.getUserPwd())) {
            return ResponseEntity.badRequest().body("현재 비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호 2번째랑 일치 하지 않을 경우
        if (!userChangePasswordDTO.getUserNewPwd().equals(userChangePasswordDTO.getUserNewPwd2())) {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
        }
        user.setUserPwd(encoder.encode(userChangePasswordDTO.getUserNewPwd()));
        userRepository.save(user);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }
}
