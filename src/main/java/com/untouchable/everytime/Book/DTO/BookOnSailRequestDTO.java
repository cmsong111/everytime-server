package com.untouchable.everytime.Book.DTO;

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
public class BookOnSailRequestDTO {
    String boardTitle;
    String content;
    //    @OneToMany
    //    ArrayList<BoardImage> images;
    String bookIsbn;
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
