package com.untouchable.everytime.article.domain

import com.untouchable.everytime.user.domain.User
import jakarta.persistence.Embeddable
import jakarta.persistence.ManyToOne

@Embeddable
data class AnonymousTable(
    /** 유저 */
    @ManyToOne
    val user: User,
    /** 익명 번호 */
    val number: Long,
)
