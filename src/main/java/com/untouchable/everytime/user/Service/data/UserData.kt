package com.untouchable.everytime.user.Service.data

import com.untouchable.everytime.user.domain.User
import com.untouchable.everytime.user.domain.UserRole
import java.time.Instant

data class UserData(
    var id: String,
    var name: String,
    var nickname: String,
    var email: String,
    var profileImage: String,
    var universityName: String,
    var entranceYear: Int,
    var role: Set<UserRole>,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object {
        fun from(user: User): UserData {
            return UserData(
                id = user.id,
                name = user.name,
                nickname = user.nickname,
                email = user.email,
                profileImage = user.profileImage,
                universityName = user.university.name,
                entranceYear = user.entranceYear,
                role = user.role,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
            )
        }
    }
}
