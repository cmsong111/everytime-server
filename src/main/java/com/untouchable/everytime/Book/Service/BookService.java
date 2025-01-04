package com.untouchable.everytime.Book.Service;

import com.untouchable.everytime.Book.DTO.BookRequestDTO;
import com.untouchable.everytime.Book.DTO.BookResponseDTO;
import com.untouchable.everytime.Book.Entity.Book;
import com.untouchable.everytime.Book.Repository.BookRepository;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository bookRepository;
    ModelMapper modelMapper;


    public ResponseEntity<BookResponseDTO> createBook(BookRequestDTO bookRequestDTO) {
        // 중복 검사
        if (bookRepository.existsById(bookRequestDTO.getIsbn())) {
            return ResponseEntity.badRequest().build();
        }
        // 책 생성 후 저장
        Book book = bookRepository.save(modelMapper.map(bookRequestDTO, Book.class));

        // 책 정보 반환
        return ResponseEntity.ok(modelMapper.map(book, BookResponseDTO.class));
    }

    public ResponseEntity<ArrayList<BookResponseDTO>> findBooksByIsbnOrName(String isbn, String name) {
        ArrayList<Book> books = bookRepository.findByIsbnOrTitleContains(isbn, name);
        ArrayList<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Book book : books) {
            bookResponseDTOS.add(modelMapper.map(book, BookResponseDTO.class));
        }
        return ResponseEntity.ok(bookResponseDTOS);
    }

    public ResponseEntity<BookResponseDTO> updateBook(BookRequestDTO bookRequestDTO) {
        if (!bookRepository.existsById(bookRequestDTO.getIsbn())) {
            return ResponseEntity.notFound().build();
        }
        Book book = bookRepository.save(modelMapper.map(bookRequestDTO, Book.class));
        return ResponseEntity.ok(modelMapper.map(book, BookResponseDTO.class));

    }

    public ResponseEntity<String> deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(isbn);
        return ResponseEntity.ok("삭제 완료");
    }


}
