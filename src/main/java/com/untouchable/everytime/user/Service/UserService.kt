package com.untouchable.everytime.user.Service

import com.untouchable.everytime.user.Service.data.UserData
import com.untouchable.everytime.user.controller.data.PasswordChangeForm
import com.untouchable.everytime.user.repository.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    /**
     * 사용자 조회
     */
    @Transactional(readOnly = true)
    fun findUserById(userId: String): UserData {
        return UserData.from(
            userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")
        )
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    fun changePassword(userId: String, passwordChangeForm: PasswordChangeForm) {
        val user = userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")

        require(passwordEncoder.matches(passwordChangeForm.currentPassword, user.password)) { "비밀번호가 일치하지 않습니다." }
        require(passwordChangeForm.newPassword == passwordChangeForm.newPasswordConfirm) { "비밀번호가 일치하지 않습니다." }

        user.setPassword(
            password = passwordChangeForm.newPassword,
            passwordEncoder = passwordEncoder
        )
    }

    /**
     * 이메일 변경
     */
    @Transactional
    fun changeEmail(userId: String, email: String): UserData {
        val user = userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")
        user.email = email
        return UserData.from(user)
    }

    /**
     * 닉네임 변경
     */
    @Transactional
    fun changeNickname(userId: String, nickname: String): UserData {
        val user = userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다.")

        user.nickname = nickname
        return UserData.from(user)
    }

    /**
     * 사용자 삭제
     */
    @Transactional
    fun deleteUser(userId: String) {
        userJpaRepository.deleteById(userId)
    }

}
