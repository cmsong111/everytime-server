package com.untouchable.everytime.article.controller

import com.untouchable.everytime.article.domain.Board
import com.untouchable.everytime.article.domain.Comment
import com.untouchable.everytime.article.domain.Post
import com.untouchable.everytime.article.repository.BoardJpaRepository
import com.untouchable.everytime.article.repository.CommentJpaRepository
import com.untouchable.everytime.article.repository.PostJpaRepository
import com.untouchable.everytime.common.domain.AuthenticatedUser
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 게시판 접근 권한 처리
 * @property boardJpaRepository 게시판 JPA 레포지토리
 */
@Component
@Transactional(readOnly = true)
class ArticleAccessHandler(
    private val boardJpaRepository: BoardJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val commentJpaRepository: CommentJpaRepository,
) {
    /**
     * 사용자가 해당 게시판에 접근 가능한지 확인
     * @param user 사용자 정보
     * @param boardId 게시판 ID
     * @return 접근 가능 여부
     */
    fun isAccessibleBoard(user: AuthenticatedUser, boardId: Long): Boolean {
        val board: Board = boardJpaRepository.findByIdOrNull(boardId) ?: return false
        return board.university.id == user.universityId
    }

    fun isAccessiblePost(user: AuthenticatedUser, postId: Long): Boolean {
        val post: Post = postJpaRepository.findByIdOrNull(postId) ?: return false
        return post.board.university.id == user.universityId
    }

    fun isAccessibleComment(user: AuthenticatedUser, commentId: Long): Boolean {
        val comment: Comment = commentJpaRepository.findByIdOrNull(commentId) ?: return false
        return comment.post.board.university.id == user.universityId
    }
}


