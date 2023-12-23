package com.untouchable.everytime.Board.Controller;

import com.untouchable.everytime.Board.DTO.BoardCommentResponseDTO;
import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Service.BoardCommentService;
import com.untouchable.everytime.Board.Service.BoardRecommendService;
import com.untouchable.everytime.Config.JwtConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Tag(name = "댓글", description = "게시글 댓글 관련 API")
@RestController
@RequestMapping("/boardComment")
@AllArgsConstructor
public class BoardCommentController {

    BoardCommentService boardCommentService;
    BoardRecommendService boardRecommendService;
    JwtConfig jwtConfig;



    @PostMapping("/{id}")
    @Operation(summary = "댓글 생성", description = "게시글에 댓글 작성하는 기능")
    public ResponseEntity<BoardCommentResponseDTO> createBoardComment(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @RequestBody BoardRequestDTO boardRequestDTO,
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return boardCommentService.addBoardComment(boardRequestDTO, id, token);
    }

    @GetMapping("/Board/{id}")
    @Operation(summary = "특정 게시글 댓글 조회", description = "해당 게시글 조회 후, JWT 에 포함된 학교와 일치 할 시 해당 게시글 댓글 리스트를 반환")
    public ResponseEntity<ArrayList<BoardCommentResponseDTO>> getBoardCommentByBoardId(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return boardCommentService.findCommentByBoard(id, token);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "댓글 수정", description = "특정 댓글을 수정하는 기능")
    public ResponseEntity<BoardCommentResponseDTO> updateBoardComment(
            @RequestBody BoardRequestDTO boardRequestDTO,
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);
        return boardCommentService.updateBoardComment(boardRequestDTO, id, token);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제", description = "특정 댓글을 삭제하는 기능")
    public ResponseEntity<String> deleteBoardComment(
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        return boardCommentService.deleteBoardComment(id, token);
    }

    @PostMapping("/{id}/recommend")
    @Operation(summary = "특정 댓글 공감하기", description = "해당 댓글 공감하는 기능")
    public ResponseEntity<String> recommendBoardComment(
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "JWT", description = "유저 토큰") @RequestHeader(value = "jwt") String token) {
        return boardCommentService.addCommentRecommend(id, token);
    }

    @PostMapping("/{id}/report")
    @Operation(summary = "특정 댓글 신고하기", description = "해당 댓글 신고하는 기능")
    public ResponseEntity<String> reportBoardComment(
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 토큰") @RequestHeader(value = "jwt") String token,
            @Parameter(name = "report", description = "신고유형") @RequestParam(name = "report") ReportType report) {
        return boardCommentService.reportCommend(id, token, report);
    }
}
