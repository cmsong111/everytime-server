package com.untouchable.everytime.user.controller.data

data class PasswordChangeForm(
    val currentPassword: String,
    val newPassword: String,
    val newPasswordConfirm: String,
)
