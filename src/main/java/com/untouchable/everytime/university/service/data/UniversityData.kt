package com.untouchable.everytime.university.service.data

import com.untouchable.everytime.university.domain.University
import com.untouchable.everytime.university.domain.UniversityLink

data class UniversityData(
    val id: Long,
    var name: String,
    var address: String,
    var zipCode: String,
    var tell: String? = null,
    var fax: String? = null,
    val pages: MutableSet<UniversityLink> = mutableSetOf()
) {
    companion object {
        fun from(university: University): UniversityData {
            return UniversityData(
                id = university.id,
                name = university.name,
                address = university.address,
                zipCode = university.zipCode,
                tell = university.tell,
                fax = university.fax,
                pages = university.pages
            )
        }
    }
}
