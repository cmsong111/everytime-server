package com.untouchable.everytime.university.service.data

import com.untouchable.everytime.university.domain.UniversityLink
import com.untouchable.everytime.university.domain.UniversityLinkType

data class UniversityLinkData(
    val type: UniversityLinkType,
    val url: String
) {
    companion object {
        fun from(universityLink: UniversityLink): UniversityLinkData {
            return UniversityLinkData(
                type = universityLink.type,
                url = universityLink.url
            )
        }
    }
}
