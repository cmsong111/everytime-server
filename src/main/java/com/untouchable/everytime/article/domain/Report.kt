package com.untouchable.everytime.article.domain

import com.untouchable.everytime.user.domain.User
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction

@Embeddable
data class Report(
    /** 신고자 */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    val reporter: User,

    /** 신고 내용 */
    val type: ReportType,
)
