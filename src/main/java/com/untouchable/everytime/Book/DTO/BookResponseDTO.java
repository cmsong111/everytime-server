package com.untouchable.everytime.Book.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDTO {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Timestamp publicationDate;
    private Long bookPrice;

}
