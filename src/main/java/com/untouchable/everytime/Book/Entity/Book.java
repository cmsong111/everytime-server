package com.untouchable.everytime.Book.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Timestamp publicationDate;
    private Long bookPrice;
}
