package com.untouchable.everytime.article.repository

import com.untouchable.everytime.article.domain.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardJpaRepository : JpaRepository<Board, Long> {
    /**
     * 대학교 ID로 게시판 목록 조회
     */
    fun findByUniversityId(id: Long): Set<Board>
}
