package com.untouchable.everytime.university.domain

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class UniversityLink(
    /** 링크 타입 */
    @Enumerated(EnumType.STRING)
    val type: UniversityLinkType,
    val url: String
)
