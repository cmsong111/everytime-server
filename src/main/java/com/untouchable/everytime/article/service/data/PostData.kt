package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.article.domain.Post
import java.time.Instant

data class PostData(
    val id: Long,
    val author: AuthorData,
    val title: String,
    val content: String,
    val images: List<String>,
    val comments: List<CommentData>,
    val likeCount: Long,
    val commentCount: Long,
    val scrapCount: Long,
    val createdAt: Instant,
) {
    companion object {
        fun from(post: Post): PostData {
            return PostData(
                id = post.id,
                author = AuthorData.from(
                    isAnonymous = post.isAnonymous,
                    author = post.author,
                ),
                title = post.title,
                content = post.content,
                images = post.images.sortedBy { it.order }.map { it.url },
                comments = post.comments.sortedBy { it.createdAt }.map { CommentData.from(it) },
                likeCount = post.likeCount,
                commentCount = post.commentCount,
                scrapCount = post.scrapCount,
                createdAt = post.createdAt,
            )
        }
    }
}
