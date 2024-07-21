package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardRecommend;
import com.untouchable.everytime.Board.Repository.BoardRecommendRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardRecommendService {


    BoardRecommendRepository boardRecommendRepository;
    BoardRepository boardRepository;
    UserRepository userRepository;


    public ResponseEntity<String> recommendBoardComment(Long id, String userName) {
        User user = userRepository.findById(userName).get();
        // 이미 추천한 경우가 있는지 확인
        List<BoardRecommend> result = boardRecommendRepository.findByBoard_BoardPk(id);
        if (result.size() > 0) {
            for (BoardRecommend boardRecommend : result) {
                if (boardRecommend.getUser().getId().equals(user.getId())) {

                    return ResponseEntity.badRequest().body("Already recommended");
                }
            }
        }

        BoardRecommend boardRecommend = new BoardRecommend();

        Optional<Board> userw = boardRepository.findById(id);
        if (userw.isPresent()) {
            boardRecommend.setBoard(userw.get());
        }
        Optional<User> userEntity = userRepository.findById(userName);
        if (userEntity.isPresent()) {
            boardRecommend.setUser(userEntity.get());
        }

        boardRecommendRepository.save(boardRecommend);

        return ResponseEntity.ok().body("친구 추가 완료");

    }


}
