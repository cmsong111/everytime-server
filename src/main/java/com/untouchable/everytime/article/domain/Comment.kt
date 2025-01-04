package com.untouchable.everytime.article.domain

import com.untouchable.everytime.common.domain.BaseEntity
import com.untouchable.everytime.user.domain.User
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
class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    /** 내용 */
    var content: String,

    /** 익명 여부 */
    var isAnonymous: Boolean = false,

    /** 작성자 */
    @ManyToOne
    val author: User,

    /** 좋아요 */
    @OneToMany(fetch = FetchType.LAZY)
    val likes: MutableSet<User> = mutableSetOf(),

    /** 좋아요 수 */
    var likeCount: Long = 0,

    /** 신고 */
    @ElementCollection
    val reports: MutableSet<Report> = mutableSetOf(),

    /** 원 게시글 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post: Post,
) : BaseEntity() {
    companion object {
        fun create(
            content: String,
            isAnonymous: Boolean,
            author: User,
            post: Post,
        ): Comment {
            return Comment(
                content = content,
                isAnonymous = isAnonymous,
                author = author,
                post = post,
            )
        }
    }

    fun update(
        content: String,
        isAnonymous: Boolean,
    ) {
        this.content = content
        this.isAnonymous = isAnonymous
    }

    /**
     * 좋아요
     * @param user 좋아요 누른 사용자
     * @return 좋아요 수
     */
    fun like(user: User): Long {
        if (!likes.contains(user)) {
            likes.add(user)
            likeCount++
        }
        return likeCount
    }

    /**
     * 좋아요 취소
     * @param user 좋아요 취소자
     * @return 좋아요 수
     */
    fun unlike(user: User): Long {
        if (likes.contains(user)) {
            likes.remove(user)
            likeCount--
        }
        return likeCount
    }

    /**
     * 신고 기능
     * @param user 신고자
     * @param reportType 신고 타입
     * @return 신고 성공 여부 (이미 신고한 경우 false)
     */
    fun report(user: User, reportType: ReportType): Boolean {
        if (!reports.any { it.reporter == user }) {
            reports.add(Report(user, reportType))
            return true
        }
        return false
    }
}
