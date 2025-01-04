package com.untouchable.everytime.Book.DTO;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDTO {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Timestamp publicationDate;
    private Long bookPrice;
}
