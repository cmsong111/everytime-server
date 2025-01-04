package com.untouchable.everytime.Book.Repository;

import com.untouchable.everytime.Book.Entity.BookOnSail;
import com.untouchable.everytime.university.domain.University;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOnSailRepository extends JpaRepository<BookOnSail, Long> {
    @Query("select b from BookOnSail b where b.school = ?1 order by b.createdAT DESC")
    List<BookOnSail> findBySchoolOrderByCreatedATDesc(University school);

    @Query("""
            select b from BookOnSail b
            where b.school = ?1 and (b.book.isbn like concat('%', ?2, '%') or b.book.title like concat('%', ?3, '%'))""")
    List<BookOnSail> findBySchoolAndBook_IsbnContainsOrBook_TitleContains(University school, String isbn, String title);


}
