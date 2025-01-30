package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.article.domain.Post
import java.time.Instant

data class PostData(
    val id: Long,
    val authorName: String,
    val authorProfileImage: String,
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
                authorName = if (post.isAnonymous) "익명" else post.author.nickname,
                authorProfileImage = if (post.isAnonymous) "https://picsum.photos/id/100/200/200" else post.author.profileImage,
                title = post.title,
                content = post.content,
                images = post.images.sortedBy { it.order }.map { it.url },
                comments = post.comments.sortedBy { it.createdAt }.map {
                    CommentData.from(
                        isPostAnonymous = post.isAnonymous,
                        isPostAuthor = post.author.id == it.author.id,
                        comment = it,
                        anonymousTables = post.anonymousTable,
                    )
                },
                likeCount = post.likeCount,
                commentCount = post.commentCount,
                scrapCount = post.scrapCount,
                createdAt = post.createdAt,
            )
        }
    }
}
