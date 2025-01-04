package com.untouchable.everytime.article.controller.data

/**
 * 댓글 작성 폼
 * @property isAnonymous 익명 여부
 * @property content 내용
 */
data class CommentForm(
    val isAnonymous: Boolean,
    val content: String,
    val postId: Long,
    val boardId: Long,
)
