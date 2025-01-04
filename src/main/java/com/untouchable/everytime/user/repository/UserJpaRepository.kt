package com.untouchable.everytime.user.repository

import com.untouchable.everytime.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<User, String> {
}
