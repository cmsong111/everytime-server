package com.untouchable.everytime.Book.Entity;

import com.untouchable.everytime.Book.Enum.BookStatus;
import com.untouchable.everytime.university.domain.University;
import com.untouchable.everytime.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookOnSail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookOnSailPk;
    @ManyToOne
    University school;
    @ManyToOne
    User user;
    String boardTitle;
    String content;
    //    @OneToMany
    //    ArrayList<BoardImage> images;
    @ManyToOne
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
