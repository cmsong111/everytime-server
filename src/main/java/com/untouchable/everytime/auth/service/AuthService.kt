package com.untouchable.everytime.auth.service

import com.untouchable.everytime.auth.TokenProvider
import com.untouchable.everytime.auth.controller.data.LoginForm
import com.untouchable.everytime.auth.controller.data.RegisterForm
import com.untouchable.everytime.auth.service.data.TokenResponse
import com.untouchable.everytime.common.domain.UserConflictException
import com.untouchable.everytime.university.repository.UniversityJpaRepository
import com.untouchable.everytime.user.domain.User
import com.untouchable.everytime.user.repository.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userJpaRepository: UserJpaRepository,
    private val universityJpaRepository: UniversityJpaRepository,
    private val tokenProvider: TokenProvider,
    private val passwordEncoder: PasswordEncoder,
) {

    /**
     * 아이디 중복 체크
     * @param id 아이디
     * @return 중복 여부 (true: 중복, false: 중복 아님)
     */
    fun checkId(id: String): Boolean {
        return userJpaRepository.existsById(id)
    }

    /**
     * 로그인
     * @param loginForm 로그인 폼
     * @return 토큰
     */
    fun login(loginForm: LoginForm): TokenResponse {
        val user = userJpaRepository.findByIdOrNull(loginForm.id)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

        require(passwordEncoder.matches(loginForm.password, user.password)) {
            "비밀번호가 일치하지 않습니다."
        }

        return createToken(user)
    }

    /**
     * 회원가입
     * @param registerForm 회원가입 폼
     * @return 토큰
     */
    fun register(registerForm: RegisterForm): TokenResponse {
        // 아이디 중복 체크
        if (userJpaRepository.existsById(registerForm.id)) {
            throw UserConflictException("이미 존재하는 아이디입니다.")
        }
        // 비밀번호 확인
        require(registerForm.password == registerForm.passwordConfirm) {
            "비밀번호가 일치하지 않습니다."
        }


        return createToken(
            userJpaRepository.save(
                User.create(
                    id = registerForm.id,
                    password = registerForm.password,
                    name = registerForm.name,
                    nickname = registerForm.nickname,
                    email = registerForm.email,
                    university = universityJpaRepository.findByIdOrNull(registerForm.universityId)
                        ?: throw IllegalArgumentException("존재하지 않는 대학입니다."),
                    entranceYear = registerForm.entranceYear,
                    passwordEncoder = passwordEncoder,
                )
            )
        )
    }

    /**
     * 토큰 생성
     * @param user 사용자
     * @return 토큰
     */
    private fun createToken(user: User): TokenResponse {
        val token = tokenProvider.createToken(
            userId = user.id,
            universityId = user.university.id,
            roles = user.role,
        )
        return TokenResponse(token)
    }
}
