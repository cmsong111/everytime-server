package com.untouchable.everytime.Board.Service;


import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.DTO.BoardResponseDTO;
import com.untouchable.everytime.Board.Entity.Board;
import com.untouchable.everytime.Board.Entity.BoardType;
import com.untouchable.everytime.Board.Repository.BoardRepository;
import com.untouchable.everytime.Board.Repository.BoardTypeRepository;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import com.untouchable.everytime.User.Entity.User;
import com.untouchable.everytime.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardService {

    BoardRepository boardRepository;
    SchoolRepository schoolRepository;
    BoardTypeRepository boardTypeRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;

    public ResponseEntity<BoardResponseDTO> addBoard(BoardRequestDTO boardRequestDTO, String username) {


        // Null Check
        // TODO: 고치기
        Optional<School> school = schoolRepository.findById(username);
        Optional<User> user = userRepository.findById(username);
        Optional<BoardType> boardType = boardTypeRepository.findById(boardRequestDTO.getBoardTypePK());
        if (school.isEmpty() || user.isEmpty() || boardType.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        // Create Board
        Board board = modelMapper.map(boardRequestDTO, Board.class);
        board.setSchool(school.get());
        board.setUser(user.get());
        board.setBoardType(boardType.get());
        board.setCreatedAT(new Timestamp(System.currentTimeMillis()));
        board.setRecommendCount(0L);
        board.setReportCount(0L);
        board.setCommentCount(0L);
        board.setScrapCount(0L);
        board.setBoardPk(null);

        Board result = boardRepository.save(board);
        return ResponseEntity.ok(modelMapper.map(result, BoardResponseDTO.class));
    }

    public ResponseEntity<BoardResponseDTO> findBoardById(Long id, String username) {
        // Null Check
        Optional<Board> boardEntity = boardRepository.findById(id);
        Optional<User> userEntity = userRepository.findById(username);
        if (boardEntity.isEmpty() || userEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
//        // Check School
//        if (!boardEntity.get().getSchool().getSchoolName().equals(jwt.get("userSchool"))) {
//            return ResponseEntity.badRequest().build();
//        }
        BoardResponseDTO boardResponseDTO = modelMapper.map(boardEntity.get(), BoardResponseDTO.class);
        // Check Anonymity
        if (boardEntity.get().isAnonymity()) {
            boardResponseDTO.setUserUserId(null);
            boardResponseDTO.setUserUserNickname(null);
        }
        return ResponseEntity.ok(boardResponseDTO);
    }

    public ResponseEntity<ArrayList<BoardResponseDTO>> findBoardsByBoardType(Long id, String username) {

        // Null Check
        Optional<BoardType> boardType = boardTypeRepository.findById(id);
        Optional<User> user = userRepository.findById(username);
        if (boardType.isEmpty() || user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

//        // Check School
//        if (!boardType.get().getSchool().getSchoolName().equals(jwt.get("userSchool"))) {
//            return ResponseEntity.badRequest().build();
//        }

        // Get Board
        ArrayList<BoardResponseDTO> boardResponseDTOS = new ArrayList<>();
        List<Board> boardEntities = boardRepository.findByBoardType_BoardTypePK(id);
        for (Board board : boardEntities) {
            BoardResponseDTO boardResponseDTO = modelMapper.map(board, BoardResponseDTO.class);
            // Check Anonymity
            if (board.isAnonymity()) {
                boardResponseDTO.setUserUserId(null);
                boardResponseDTO.setUserUserNickname(null);
            }
            boardResponseDTOS.add(boardResponseDTO);
        }
        return ResponseEntity.ok(boardResponseDTOS);
    }


    public ResponseEntity<BoardResponseDTO> modifyBoard(Long id, BoardRequestDTO boardRequestDTO, String username) {

        // Null Check
//        Optional<School> school = schoolRepository.findById(String.valueOf(jwt.get("userSchool")));
        Optional<User> user = userRepository.findById(username);
        Optional<Board> board = boardRepository.findById(id);
//        if (school.isEmpty() || user.isEmpty() || board.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }

        // Update Board
        Board boardEntity = board.get();
        boardEntity.setBoardTitle(boardRequestDTO.getBoardTitle());
        boardEntity.setContent(boardRequestDTO.getContent());


        Board result = boardRepository.save(boardEntity);
        return ResponseEntity.ok(modelMapper.map(result, BoardResponseDTO.class));
    }

    public ResponseEntity<String> removeBoard(Long id, String username) {

        // Null Check
        Optional<Board> board = boardRepository.findById(id);
        Optional<User> user = userRepository.findById(username);
        if (board.isEmpty() || user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Delete Board
        if (board.get().getUser().equals(user.get())) {
            boardRepository.deleteById(id);
            return ResponseEntity.ok("Delete Success");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
