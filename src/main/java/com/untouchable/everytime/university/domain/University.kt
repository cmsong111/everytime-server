package com.untouchable.everytime.university.domain

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class University(
    id: Long = 0L,
    name: String,
    address: String,
    zipCode: String,
    tell: String? = null,
    fax: String? = null,
    pages: MutableSet<UniversityLink> = mutableSetOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = id

    /** 대학 이름 */
    var name: String = name

    /** 주소 */
    var address: String = address

    /** 우편번호 */
    var zipCode: String = zipCode

    /** 대표 전화번호 */
    var tell: String? = tell

    /** 팩스 */
    var fax: String? = fax

    /** 홈페이지 */
    @ElementCollection
    val pages: MutableSet<UniversityLink> = pages

    companion object {
        fun create(
            name: String,
            address: String,
            zipCode: String,
            tell: String? = null,
            fax: String? = null,
            pages: Set<UniversityLink> = setOf()
        ): University {
            return University(
                name = name,
                address = address,
                zipCode = zipCode,
                tell = tell,
                fax = fax,
                pages = pages.toMutableSet()
            )
        }
    }
}


