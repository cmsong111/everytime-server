package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.DTO.BoardCommentResponseDTO;
import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardComment;
import com.untouchable.everytime.Board.Entity.BoardCommentRecommend;
import com.untouchable.everytime.Board.Entity.BoardCommentReport;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Repository.BoardCommentRecommendRepository;
import com.untouchable.everytime.Board.Repository.BoardCommentReportRepository;
import com.untouchable.everytime.Board.Repository.BoardCommentRepository;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {
    BoardCommentRepository boardCommentRepository;
    BoardCommentRecommendRepository boardCommentRecommendRepository;
    BoardCommentReportRepository boardCommentReportRepository;
    BoardRepository boardRepository;
    UserRepository userRepository;
    SchoolRepository schoolRepository;
    ModelMapper modelMapper;


    public ResponseEntity<BoardCommentResponseDTO> addBoardComment(BoardRequestDTO boardRequestDTO, Long id, String username, Claims claims) {

        // Null check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        BoardComment boardComment = modelMapper.map(boardRequestDTO, BoardComment.class);
        boardComment.setBoard(boardEntity.get());
        boardComment.setUser(userEntity.get());
        boardComment.setCreatedAT(new Timestamp(System.currentTimeMillis()));
        boardComment.setReportCount(0L);
        boardComment.setRecommendCount(0L);

        return ResponseEntity.ok(modelMapper.map(boardCommentRepository.save(boardComment), BoardCommentResponseDTO.class));
    }

    public ResponseEntity<ArrayList<BoardCommentResponseDTO>> findCommentByBoard(Long id, String username, Claims claims) {
        // Null check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        Optional<School> schoolEntity = schoolRepository.findById(claims.get("userSchool").toString());
        if (boardEntity.isEmpty() || userEntity.isEmpty() || schoolEntity.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        // 학교 체크
        if (!boardEntity.get().getSchool().equals(schoolEntity.get())) {
            ResponseEntity.badRequest().build();
        }

        List<BoardComment> result = boardCommentRepository.findByBoard_BoardPk(id);

        ArrayList<BoardCommentResponseDTO> resultDTO = new ArrayList<>();

        for (BoardComment boardComment : result) {
            BoardCommentResponseDTO boardCommentResponseDTO = modelMapper.map(boardComment, BoardCommentResponseDTO.class);
            if (boardCommentResponseDTO.isAnonymity()) {
                boardCommentResponseDTO.setUserUserID(null);
                boardCommentResponseDTO.setUserUserNickname(null);
            }
            resultDTO.add(boardCommentResponseDTO);
        }

        return ResponseEntity.ok(resultDTO);

    }

    public ResponseEntity<BoardCommentResponseDTO> updateBoardComment(BoardRequestDTO boardRequestDTO, Long id, String username) {


        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            ResponseEntity.badRequest().build();
        }

        // 작성자 확인
        if (!boardCommentEntity.get().getUser().equals(userEntity.get())) {
            ResponseEntity.badRequest().build();
        }

        // 수정
        BoardComment boardComment = boardCommentEntity.get();
        boardComment.setContent(boardRequestDTO.getContent());

        return ResponseEntity.ok(modelMapper.map(boardCommentRepository.save(boardComment), BoardCommentResponseDTO.class));
    }

    public ResponseEntity<String> deleteBoardComment(Long id, String username) {

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 작성자 확인
        if (!boardCommentEntity.get().getUser().equals(userEntity.get())) {
            return ResponseEntity.badRequest().build();
        }

        boardCommentRepository.deleteById(id);

        return ResponseEntity.ok("삭제되었습니다.");
    }

    public ResponseEntity<String> addCommentRecommend(Long id, String username) {

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 추천 확인
        if (boardCommentRecommendRepository.existsByBoardCommentAndUser(boardCommentEntity.get(), userEntity.get())) {
            return ResponseEntity.badRequest().body("이미 추천하셨습니다.");
        }
        // 추천 추가
        BoardCommentRecommend boardCommentRecommend = new BoardCommentRecommend();
        boardCommentRecommend.setBoardComment(boardCommentEntity.get());
        boardCommentRecommend.setUser(userEntity.get());
        boardCommentRecommendRepository.save(boardCommentRecommend);

        // 추천 수 최신화
        BoardComment boardComment = boardCommentEntity.get();
        boardComment.setRecommendCount(boardCommentRecommendRepository.countByBoardComment(boardComment));
        boardCommentRepository.save(boardComment);

        return ResponseEntity.ok("추천되었습니다.");
    }

    public ResponseEntity<String> reportCommend(Long id, String username, ReportType reportType) {

        Optional<BoardComment> boardCommentEntity = boardCommentRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        if (boardCommentEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 신고 확인
        if (boardCommentReportRepository.existsByReportUserAndReportBoardComment(userEntity.get(), boardCommentEntity.get())) {
            return ResponseEntity.badRequest().body("이미 신고하셨습니다.");
        }

        // 신고
        BoardCommentReport boardCommentReport = new BoardCommentReport();
        boardCommentReport.setReportUser(userEntity.get());
        boardCommentReport.setReportBoardComment(boardCommentEntity.get());
        boardCommentReport.setReportType(reportType);


        boardCommentReportRepository.save(boardCommentReport);

        // 신고 수 최신화
        BoardComment boardComment = boardCommentEntity.get();
        boardComment.setReportCount(boardCommentReportRepository.countByReportBoardComment(boardComment));
        boardCommentRepository.save(boardComment);

        return ResponseEntity.ok("신고되었습니다.");
    }


}

