package com.untouchable.everytime.Book.DTO;

import com.untouchable.everytime.Book.Entity.Book;
import com.untouchable.everytime.Book.Enum.BookStatus;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookOnSailResponseDTO {
    Long bookOnSailPk;
    String boardTitle;
    String content;
    //    @OneToMany
    //    ArrayList<BoardImage> images;
    Book book;
    Timestamp createdAT;
    BookStatus underLined;
    BookStatus written;
    boolean external;
    boolean naming;
    boolean colorChanged;
    boolean damaged;
    boolean delivery;
    boolean direct;
    String meetingPlaces;
    int sellPrice;
}
