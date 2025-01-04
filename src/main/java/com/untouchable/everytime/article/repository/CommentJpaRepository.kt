package com.untouchable.everytime.article.repository

import com.untouchable.everytime.article.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentJpaRepository : JpaRepository<Comment, Long> {
}
