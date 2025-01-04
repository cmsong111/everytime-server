package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.user.domain.User

data class AuthorData(
    val name: String,
    val profileImage: String
) {
    companion object {
        fun from(
            isAnonymous: Boolean,
            author: User
        ): AuthorData {
            return if (isAnonymous) {
                AuthorData(
                    name = "익명",
                    profileImage = "https://picsum.photos/id/100/200/200"
                )
            } else {
                AuthorData(
                    name = author.nickname,
                    profileImage = author.profileImage
                )
            }
        }
    }

}
