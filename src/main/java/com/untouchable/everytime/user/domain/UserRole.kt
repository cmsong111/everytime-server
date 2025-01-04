package com.untouchable.everytime.user.domain

import org.springframework.security.core.GrantedAuthority

enum class UserRole : GrantedAuthority {
    USER, MANAGER, ADMIN;

    override fun getAuthority(): String {
        return "ROLE_$name"
    }
}
