package com.untouchable.everytime.article.repository

import com.untouchable.everytime.article.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostJpaRepository : JpaRepository<Post, Long> {
    fun findByBoardId(boardId: Long, pageable: Pageable): Page<Post>
}
