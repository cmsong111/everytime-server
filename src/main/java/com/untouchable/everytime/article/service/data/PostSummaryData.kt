package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.article.domain.Post
import java.time.Instant

data class PostSummaryData(
    val id: Long,
    val authorName: String,
    val title: String,
    val content: String,
    val thumbnail: String?,
    val likeCount: Long,
    val commentCount: Long,
    val createdAt: Instant,
) {
    companion object {
        fun from(post: Post): PostSummaryData {
            return PostSummaryData(
                id = post.id,
                authorName = AuthorData.from(
                    isAnonymous = post.isAnonymous,
                    author = post.author,
                ).name,
                title = post.title,
                content = post.content,
                thumbnail = post.images.minByOrNull { it.order }?.url,
                likeCount = post.likeCount,
                commentCount = post.commentCount,
                createdAt = post.createdAt,
            )
        }
    }
}
