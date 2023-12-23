package com.untouchable.everytime.Board.Repository;

import com.untouchable.everytime.Board.Entity.BoardComment;
import com.untouchable.everytime.Board.Entity.BoardCommentRecommend;
import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardCommentRecommendRepository extends JpaRepository<BoardCommentRecommend, Long> {
    @Query("select (count(b) > 0) from BoardCommentRecommend b where b.boardComment = ?1 and b.user = ?2")
    boolean existsByBoardCommentAndUser(BoardComment boardComment, User user);
    @Query("select count(b) from BoardCommentRecommend b where b.boardComment = ?1")
    long countByBoardComment(BoardComment boardComment);
}
