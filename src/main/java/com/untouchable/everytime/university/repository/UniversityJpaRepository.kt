package com.untouchable.everytime.university.repository

import com.untouchable.everytime.university.domain.University
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UniversityJpaRepository : JpaRepository<University, Long> {
    @Query(
        """
        SELECT u FROM University u 
        WHERE (:name IS NULL OR u.name LIKE %:name%) 
    """
    )
    fun findByNameNullable(name: String?, pageable: Pageable): Page<University>
}
