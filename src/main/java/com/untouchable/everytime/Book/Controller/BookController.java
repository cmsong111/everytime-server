package com.untouchable.everytime.Book.Controller;

import com.untouchable.everytime.Book.DTO.BookRequestDTO;
import com.untouchable.everytime.Book.DTO.BookResponseDTO;
import com.untouchable.everytime.Book.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "책 관련 API")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    @Operation(summary = "책 등록", description = "책을 등록하는 기능")
    @ApiResponse(responseCode = "200", description = "책 등록 성공")
    @ApiResponse(responseCode = "400", description = "책 등록 실패")
    public ResponseEntity<BookResponseDTO> createBook(
            @Parameter(name = "책 정보 입력") @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    @GetMapping("/find")
    @Operation(summary = "책 검색", description = "책을 검색하는 기능")
    @ApiResponse(responseCode = "200", description = "책 검색 성공")
    @ApiResponse(responseCode = "400", description = "책 검색 실패")
    public ResponseEntity<ArrayList<BookResponseDTO>> findBook(
            @Parameter(name = "nameOrIsbn") @RequestParam(name = "nameOrIsbn") String nameOrIsbn
    ) {
        return bookService.findBooksByIsbnOrName(nameOrIsbn, nameOrIsbn);
    }

    @PatchMapping("/update")
    @Operation(summary = "책 수정", description = "책을 수정하는 기능")
    @ApiResponse(responseCode = "200", description = "책 수정 성공")
    @ApiResponse(responseCode = "400", description = "책 수정 실패")
    public ResponseEntity<BookResponseDTO> updateBook(
            @Parameter(name = "책 정보 입력") @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.updateBook(bookRequestDTO);
    }

    @DeleteMapping("/{isbn}/delete")
    @Operation(summary = "책 삭제", description = "책을 삭제하는 기능")
    @ApiResponse(responseCode = "200", description = "책 삭제 성공")
    @ApiResponse(responseCode = "400", description = "책 삭제 실패")
    public ResponseEntity<String> deleteBook(
            @Parameter(name = "isbn", description = "isbn",in = ParameterIn.PATH) @PathVariable("isbn") String isbn) {
        return bookService.deleteBook(isbn);
    }
}
