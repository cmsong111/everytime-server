package com.untouchable.everytime.article.domain

import com.untouchable.everytime.article.controller.data.PostForm
import com.untouchable.everytime.common.domain.BaseEntity
import com.untouchable.everytime.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Post(
    /** 게시글 아이디 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    /** 게시판 종류 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board,

    @ManyToOne(fetch = FetchType.LAZY)
    val author: User,

    /** 익명 여부 */
    var isAnonymous: Boolean = false,

    /** 제목 */
    var title: String,

    /** 내용 */
    @Column(columnDefinition = "TEXT")
    var content: String,

    /** 이미지들 */
    @ElementCollection(fetch = FetchType.LAZY)
    val images: Set<Image> = setOf(),

    /** 좋아요 */
    @OneToMany(fetch = FetchType.LAZY)
    val likes: MutableSet<User> = mutableSetOf(),

    /** 좋아요 수 */
    var likeCount: Long = 0L,

    /** 댓글 목록 */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    val comments: MutableSet<Comment> = mutableSetOf(),

    /** 댓글 수 */
    var commentCount: Long = 0L,

    /** 스크랩 */
    @OneToMany(fetch = FetchType.LAZY)
    val scraps: MutableSet<User> = mutableSetOf(),

    /** 스크랩 수 */
    var scrapCount: Long = 0L,

    /** 신고 */
    @ElementCollection
    val reports: MutableSet<Report> = mutableSetOf(),
) : BaseEntity() {
    companion object {
        fun create(
            board: Board,
            author: User,
            postForm: PostForm,
        ): Post {
            return Post(
                board = board,
                author = author,
                isAnonymous = postForm.isAnonymous,
                title = postForm.title,
                content = postForm.content,
            )
        }
    }

    fun update(postForm: PostForm) {
        this.title = postForm.title
        this.content = postForm.content
        this.isAnonymous = postForm.isAnonymous
    }
}

