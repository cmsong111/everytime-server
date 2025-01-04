package com.untouchable.everytime.article.domain

import com.untouchable.everytime.common.domain.BaseEntity
import com.untouchable.everytime.university.domain.University
import com.untouchable.everytime.user.domain.User
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

/**
 * 게시판 종류 엔티티 (자유게시판, 익명게시판, ...)
 * @property id 게시판 종류 아이디
 * @property name 게시판 종류 이름
 * @property description 게시판 종류 설명
 * @property manager 게시판 관리자
 */
@Entity
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    /** 게시판 이름 */
    var name: String,

    /** 설명 */
    var description: String,

    /** 게시판 유형 **/
    @Enumerated(EnumType.STRING)
    val boardType: BoardType,

    /** 관리자 */
    @ManyToOne
    val manager: User,

    /** 소속 학교 */
    @ManyToOne(fetch = FetchType.LAZY)
    val university: University
) : BaseEntity()
