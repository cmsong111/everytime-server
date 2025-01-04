package com.untouchable.everytime.article.service

import com.untouchable.everytime.article.repository.BoardJpaRepository
import com.untouchable.everytime.article.service.data.BoardData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 게시판 서비스 (자유 게시판, 익명 게시판, 공지사항 등)
 */
@Service
class BoardService(
    private val boardJpaRepository: BoardJpaRepository,
) {
    /**
     * 대학교 ID로 게시판 목록 조회
     * @param universityId 대학교 ID
     * @return 게시판 목록
     */
    @Transactional(readOnly = true)
    fun getBoardsByUniversityId(universityId: Long): List<BoardData> {
        return boardJpaRepository.findByUniversityId(universityId).map {
            BoardData.from(it)
        }.sortedBy { it.boardType }
    }
}
