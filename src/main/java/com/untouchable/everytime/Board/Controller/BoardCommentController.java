package com.untouchable.everytime.Board.Controller;

import com.untouchable.everytime.Board.DTO.BoardCommentResponseDTO;
import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Service.BoardCommentService;
import com.untouchable.everytime.Board.Service.BoardRecommendService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Tag(name = "댓글", description = "게시글 댓글 관련 API")
@RestController
@RequestMapping("/boardComment")
@AllArgsConstructor
public class BoardCommentController {

    BoardCommentService boardCommentService;
    BoardRecommendService boardRecommendService;


    @PostMapping("/{id}")
    @Operation(summary = "댓글 생성", description = "게시글에 댓글 작성하는 기능")
    public ResponseEntity<BoardCommentResponseDTO> createBoardComment(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @RequestBody BoardRequestDTO boardRequestDTO,
            Principal principal, Claims claims) {
        return boardCommentService.addBoardComment(boardRequestDTO, id, principal.getName(), claims);
    }

    @GetMapping("/Board/{id}")
    @Operation(summary = "특정 게시글 댓글 조회", description = "해당 게시글 조회 후, JWT 에 포함된 학교와 일치 할 시 해당 게시글 댓글 리스트를 반환")
    public ResponseEntity<ArrayList<BoardCommentResponseDTO>> getBoardCommentByBoardId(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            Principal principal, Claims claims) {
        return boardCommentService.findCommentByBoard(id, principal.getName(), claims);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "댓글 수정", description = "특정 댓글을 수정하는 기능")
    public ResponseEntity<BoardCommentResponseDTO> updateBoardComment(
            @RequestBody BoardRequestDTO boardRequestDTO,
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            Principal principal, Claims claims) {
        return boardCommentService.updateBoardComment(boardRequestDTO, id, principal.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제", description = "특정 댓글을 삭제하는 기능")
    public ResponseEntity<String> deleteBoardComment(
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            Principal principal, Claims claims) {
        return boardCommentService.deleteBoardComment(id, principal.getName());
    }

    @PostMapping("/{id}/recommend")
    @Operation(summary = "특정 댓글 공감하기", description = "해당 댓글 공감하는 기능")
    public ResponseEntity<String> recommendBoardComment(
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            Principal principal, Claims claims) {
        return boardCommentService.addCommentRecommend(id, principal.getName());
    }

    @PostMapping("/{id}/report")
    @Operation(summary = "특정 댓글 신고하기", description = "해당 댓글 신고하는 기능")
    public ResponseEntity<String> reportBoardComment(
            @Parameter(name = "id", description = "댓글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "report", description = "신고유형") @RequestParam(name = "report") ReportType report,
            Principal principal, Claims claims) {
        return boardCommentService.reportCommend(id, principal.getName(), report);
    }
}
