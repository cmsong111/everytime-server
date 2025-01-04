package com.untouchable.everytime.Book.Repository;

import com.untouchable.everytime.Book.Entity.Book;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("select b from Book b where b.isbn = ?1 or b.title like concat('%', ?2, '%')")
    ArrayList<Book> findByIsbnOrTitleContains(String isbn, String title);


}

