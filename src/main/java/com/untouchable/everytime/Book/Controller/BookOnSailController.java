package com.untouchable.everytime.Book.Controller;

import com.untouchable.everytime.Book.DTO.BookOnSailRequestDTO;
import com.untouchable.everytime.Book.DTO.BookOnSailResponseDTO;
import com.untouchable.everytime.Book.Service.BookOnSailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "BookOnSail", description = "책 판매 관련 API")
@RestController
@AllArgsConstructor
@RequestMapping("/bookOnSail")
public class BookOnSailController {

    BookOnSailService bookOnSailService;

    @PostMapping("/create")
    @Operation(summary = "책 판매 등록", description = "책 판매글을 등록하는 기능")
    @ApiResponse(responseCode = "200", description = "책 판매 등록 성공")
    @ApiResponse(responseCode = "400", description = "책 판매 등록 실패")
    public ResponseEntity<BookOnSailResponseDTO> createBookOnSail(
            @Parameter(name = "jwt", description = "jwt", in = ParameterIn.HEADER) @RequestHeader(name = "jwt") String token,
            @Parameter(name = "책 정보 입력") @RequestBody BookOnSailRequestDTO bookOnSailRequestDTO) {
        return null;
    }

    @GetMapping("/find")
    @Operation(summary = "책 판매 검색", description = "책을 검색하는 기능")
    @ApiResponse(responseCode = "200", description = "책 판매 조회 성공")
    @ApiResponse(responseCode = "400", description = "책 판매 조회 실패")
    public ResponseEntity<ArrayList<BookOnSailResponseDTO>> findBookOnSail(
            @Parameter(name = "jwt", description = "jwt", in = ParameterIn.HEADER) @RequestHeader(name = "jwt") String token,
            @Parameter(name = "bookNameOrIsbn", in = ParameterIn.PATH) @PathVariable String bookNameOrIsbn) {
        return null;
    }

    @GetMapping("/findAll")
    @Operation(summary = "책 판매 전체 조회(학교 필터)", description = "책 판매글을 학교에 맞게 조회하는 기능")
    @ApiResponse(responseCode = "200", description = "책 판매 전체 조회 성공")
    @ApiResponse(responseCode = "400", description = "책 판매 전체 조회 실패")
    public ResponseEntity<ArrayList<BookOnSailResponseDTO>> findAllBookOnSail(
            @Parameter(name = "jwt", description = "jwt", in = ParameterIn.HEADER) @RequestHeader(name = "jwt") String token
    ) {
        return null;
    }

    @PatchMapping("/update")
    @Operation(summary = "책 판매 수정", description = "책 판매글을 수정하는 기능")
    @ApiResponse(responseCode = "200", description = "책 판매 수정 성공")
    @ApiResponse(responseCode = "400", description = "책 판매 수정 실패")
    public ResponseEntity<BookOnSailResponseDTO> updateBookOnSail(
            @Parameter(name = "jwt", description = "jwt", in = ParameterIn.HEADER) @RequestHeader(name = "jwt") String token,
            @Parameter(name = "bookOnSailId", in = ParameterIn.PATH) @PathVariable String bookOnSailId,
            @Parameter(name = "책 정보 입력") @RequestBody BookOnSailRequestDTO bookOnSailRequestDTO) {
        return null;
    }

    @DeleteMapping("/delete")
    @Operation(summary = "책 판매 삭제", description = "책 판매글을 삭제하는 기능")
    @ApiResponse(responseCode = "200", description = "책 판매 삭제 성공")
    @ApiResponse(responseCode = "400", description = "책 판매 삭제 실패")
    public ResponseEntity<String> deleteBookOnSail(
            @Parameter(name = "jwt", description = "jwt", in = ParameterIn.HEADER) @RequestHeader(name = "jwt") String token,
            @Parameter(name = "bookOnSailId", in = ParameterIn.PATH) @PathVariable String bookOnSailId
    ) {
        return null;
    }
}
