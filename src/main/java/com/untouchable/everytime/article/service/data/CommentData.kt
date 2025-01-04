package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.article.domain.Comment

data class CommentData(
    val id: Long,
    val content: String,
    val authorData: AuthorData,
    val likeCount: Long,
) {
    companion object {
        fun from(comment: Comment): CommentData {
            return CommentData(
                id = comment.id,
                content = comment.content,
                authorData = AuthorData.from(
                    isAnonymous = comment.isAnonymous,
                    author = comment.author,
                ),
                likeCount = comment.likeCount,
            )
        }
    }
}
