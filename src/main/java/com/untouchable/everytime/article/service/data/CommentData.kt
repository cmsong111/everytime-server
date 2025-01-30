package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.article.domain.AnonymousTable
import com.untouchable.everytime.article.domain.Comment
import java.time.Instant
import org.springframework.data.jpa.domain.AbstractPersistable_.id

data class CommentData(
    val id: Long,
    val content: String,
    val authorName: String,
    val authorProfileImage: String,
    val likeCount: Long,
    val createdAt: Instant,
) {
    companion object {
        fun from(
            isPostAnonymous: Boolean,
            isPostAuthor: Boolean,
            comment: Comment,
            anonymousTables: Set<AnonymousTable>,
        ): CommentData {
            // comment.isAnonymous && isPostAuthor -> 익명(작성자)
            // comment.isAnonymous && !isPostAuthor -> 익명(익명1, 익명2, ...)
            // !comment.isAnonymous -> author.nickname
            val responseAuthorName: String = if (comment.isAnonymous) {
                if (isPostAuthor && isPostAnonymous) {
                    "익명(작성자)"
                } else {
                    val anonymousTable = anonymousTables.find { it.user.id == comment.author.id }
                    "익명 ${anonymousTable?.number}"
                }
            } else {
                comment.author.nickname
            }

            return CommentData(
                id = comment.id,
                content = comment.content,
                authorName = responseAuthorName,
                authorProfileImage = if (comment.isAnonymous) "https://picsum.photos/id/100/200/200" else comment.author.profileImage,
                likeCount = comment.likeCount,
                createdAt = comment.createdAt,
            )
        }
    }
}
