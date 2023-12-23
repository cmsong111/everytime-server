package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.DTO.BoardResponseDTO;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardScrap;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.Board.Repository.BoardScrapRepository;
import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardScrapService {
    BoardScrapRepository boardScrapRepository;
    UserRepository userRepository;
    BoardRepository boardRepository;
    JwtConfig jwtConfig;
    ModelMapper modelMapper;

    public ResponseEntity<ArrayList<BoardResponseDTO>> getMyScrap(String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (userEntity.isEmpty()) {
            return null;
        }

        List<BoardScrap> resultEntity = boardScrapRepository.findByUser(userEntity.get());
        ArrayList<BoardResponseDTO> result = new ArrayList<>();

        for (BoardScrap entity : resultEntity) {
            BoardResponseDTO boardResponseDTO = modelMapper.map(entity.getBoard(), BoardResponseDTO.class);
            if (boardResponseDTO.isAnonymity()){
                boardResponseDTO.setUserUserNickname("null");
                boardResponseDTO.setUserUserId("null");
            }
            result.add(boardResponseDTO);
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> scrapBoard(Long id, String token){
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null Check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));

        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // 학교 체크
        if (!boardEntity.get().getSchool().equals(userEntity.get().getUserSchool())) {
            System.out.println("학교 불일치");
            return ResponseEntity.badRequest().build();
        }
        // 중복 체크
        if (boardScrapRepository.findByBoardAndUser(boardEntity.get(), userEntity.get()).isPresent()) {
            System.out.println("중복 스크랩");
            return ResponseEntity.badRequest().build();
        }

        // 스크랩 히스토리 저장
        BoardScrap boardScrap = new BoardScrap();
        boardScrap.setBoard(boardEntity.get());
        boardScrap.setUser(userEntity.get());
        boardScrapRepository.save(boardScrap);

        // 스크랩 카운트 수정
        Long scrapCount = boardScrapRepository.countByBoard(boardEntity.get());
        boardEntity.get().setScrapCount(scrapCount);
        boardRepository.save(boardEntity.get());
        
        return ResponseEntity.ok("스크랩 성공");
    }

    public ResponseEntity<String> unScrapBoard(Long id, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        // Null Check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(String.valueOf(jwt.get("userId")));
        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 스크랩 조회
        Optional<BoardScrap> boardScrap = boardScrapRepository.findByBoardAndUser(boardEntity.get(), userEntity.get());
        if (boardScrap.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // 스크랩 히스토리 삭제
        boardScrapRepository.delete(boardScrap.get());

        // 스크랩 카운트 수정
        Long scrapCount = boardScrapRepository.countByBoard(boardEntity.get());
        boardEntity.get().setScrapCount(scrapCount);
        boardRepository.save(boardEntity.get());

        return ResponseEntity.ok("스크랩 취소 성공");
    }
}

