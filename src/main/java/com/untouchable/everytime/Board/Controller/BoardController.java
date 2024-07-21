package com.untouchable.everytime.Board.Controller;

import com.untouchable.everytime.Board.DTO.BoardRequestDTO;
import com.untouchable.everytime.Board.DTO.BoardResponseDTO;
import com.untouchable.everytime.Board.Enum.ReportType;
import com.untouchable.everytime.Board.Service.BoardReportService;
import com.untouchable.everytime.Board.Service.BoardScrapService;
import com.untouchable.everytime.Board.Service.BoardService;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Tag(name = "게시글", description = "게시글 CRUD 관련 API")
@RestController
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    BoardScrapService boardScrapService;
    BoardReportService boardReportService;
    BoardService boardService;


    @GetMapping("/{id}")
    @Operation(summary = "게시글 조회", description = "게시글 PK와, JWT를 입력받아 회원 인증 후 특정 게시물 조회 하는 기능")
    public ResponseEntity<BoardResponseDTO> getBoard(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            Principal principal) {
        return boardService.findBoardById(id, principal.getName());
    }


    @PostMapping("/{id}/report")
    @Operation(summary = "특정 게시물 신고", description = "게시글 PK와, JWT를 입력받아 회원 인증 후 특정 게시물 신고 하는 기능")
    @ApiResponse(responseCode = "200", description = "신고 성공")
    @ApiResponse(responseCode = "400", description = "신고 실패")
    public ResponseEntity<String> reportBoard(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            Principal principal,
            @Parameter(name = "report", description = "신고유형") @RequestParam(name = "report") ReportType report,
            Claims claims) {
        return boardReportService.reportBoard(id, principal.getName(), report);
    }

    @GetMapping("/getBoardByBoardType/{boardTypeId}")
    @Operation(summary = "특정 게시판들 글만 조회", description = "ex)동의대학교 자유게시판 조회")
    public ResponseEntity<ArrayList<BoardResponseDTO>> getBoardByBoardType(
            @Parameter(name = "boardTypeId", description = "특정 게시판 PK", in = ParameterIn.PATH) @PathVariable("boardTypeId") Long boardTypeId,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {

        return boardService.findBoardsByBoardType(boardTypeId, token);
    }

    @PostMapping("/create")
    @Operation(summary = "게시글 생성", description = "게시글 새로 생성하는 기능")
    public ResponseEntity<BoardResponseDTO> createBoard(
            @RequestBody BoardRequestDTO boardRequestDTO,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {
        return boardService.addBoard(boardRequestDTO, token);
    }

    @PatchMapping("/{id}/update")
    @Operation(summary = "게시글 수정", description = "게시글 수정 하는 기능")
    public ResponseEntity<BoardResponseDTO> updateBoard(
            @RequestBody BoardRequestDTO boardRequestDTO,
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰", in = ParameterIn.HEADER) @RequestHeader(value = "jwt") String token) {


        return boardService.modifyBoard(id = id, boardRequestDTO = boardRequestDTO, token = token);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "게시글 삭제", description = "게시글 삭제하는 기능")
    public ResponseEntity<String> deleteBoard(
            @Parameter(name = "게시글 PK", description = "게시글 PK") @RequestParam("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        return boardService.removeBoard(id, token);
    }

    @PostMapping("/{id}/scrap")
    @Operation(summary = "게시글 스크랩", description = "게시글 스크랩 하는 기능")
    public ResponseEntity<String> addScrapBoard(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        return boardScrapService.scrapBoard(id, token);
    }

    @GetMapping("/getMyScrap")
    @Operation(summary = "스크랩한 글 조회", description = "스크랩 한 글들을 조회하는 기능")
    public ResponseEntity<ArrayList<BoardResponseDTO>> getMyScrap(
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        return boardScrapService.getMyScrap(token);
    }

    @DeleteMapping("{id}/unScrap")
    @Operation(summary = "스크랩한 글 삭제", description = "스크랩 한 글을 삭제하는 기능")
    public ResponseEntity<String> removeScrap(
            @Parameter(name = "id", description = "게시글 PK", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token) {
        return boardScrapService.unScrapBoard(id, token);
    }
}
