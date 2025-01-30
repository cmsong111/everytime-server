package com.untouchable.everytime.article.service

import com.untouchable.everytime.article.controller.data.CommentForm
import com.untouchable.everytime.article.domain.AnonymousTable
import com.untouchable.everytime.article.domain.Comment
import com.untouchable.everytime.article.domain.Post
import com.untouchable.everytime.article.domain.ReportType
import com.untouchable.everytime.article.repository.CommentJpaRepository
import com.untouchable.everytime.article.repository.PostJpaRepository
import com.untouchable.everytime.user.repository.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val postJpaRepository: PostJpaRepository,
    private val commentJpaRepository: CommentJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {
    /**
     * 댓글 작성
     */
    @Transactional
    fun createComment(
        commentForm: CommentForm,
        userId: String,
    ) {
        val post: Post = postJpaRepository.findByIdOrNull(commentForm.postId)
            ?: throw IllegalArgumentException("게시글이 존재하지 않습니다.")

        val user = userJpaRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("사용자가 존재하지 않습니다.")

        // 익명인 경우 익명 번호 발급
        // 공개 게시글에 본인이 익명으로 댓글을 달 경우 익명 번호 발급
        if (commentForm.isAnonymous && !post.anonymousTable.map { it.user }.contains(user)) {
            if (post.author.id == userId && post.isAnonymous) {
                // 본인의 익명 게시글에 익명 댓글을 달 경우 익명 번호 발급하지 않음
            } else {
                // 익명 최대번호 조회
                val maxNumber = post.anonymousTable.maxOfOrNull { it.number } ?: 0
                post.anonymousTable.add(
                    AnonymousTable(
                        user = user,
                        number = maxNumber + 1,
                    )
                )
            }
        }

        commentJpaRepository.save(
            Comment.create(
                content = commentForm.content,
                isAnonymous = commentForm.isAnonymous,
                author = user,
                post = post,
            )
        )

        post.commentCount++
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    fun deleteComment(
        commentId: Long,
        userId: String,
    ) {
        val comment: Comment = commentJpaRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("댓글이 존재하지 않습니다.")

        require(comment.author.id == userId) { "댓글 작성자만 삭제할 수 있습니다." }

        commentJpaRepository.delete(comment)
    }

    /**
     * 댓글 좋아요
     */
    @Transactional
    fun likeComment(
        commentId: Long,
        userId: String,
    ): Long {
        val comment: Comment = commentJpaRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("댓글이 존재하지 않습니다.")

        val user = userJpaRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("사용자가 존재하지 않습니다.")

        comment.like(user)
        return comment.likeCount
    }

    /**
     * 댓글 좋아요 취소
     */
    @Transactional
    fun unlikeComment(
        commentId: Long,
        userId: String,
    ): Long {
        val comment: Comment = commentJpaRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("댓글이 존재하지 않습니다.")

        val user = userJpaRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("사용자가 존재하지 않습니다.")

        comment.unlike(user)
        return comment.likeCount
    }

    /**
     * 댓글 신고 (신고 횟수 증가)
     */
    @Transactional
    fun reportComment(
        commentId: Long,
        userId: String,
        reportType: ReportType,
    ): Boolean {
        val comment: Comment = commentJpaRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("댓글이 존재하지 않습니다.")

        val user = userJpaRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("사용자가 존재하지 않습니다.")
        return comment.report(user, reportType)
    }
}
