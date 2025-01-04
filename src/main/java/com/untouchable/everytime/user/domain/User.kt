package com.untouchable.everytime.user.domain

import com.untouchable.everytime.common.domain.BaseEntity
import com.untouchable.everytime.university.domain.University
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "users")
class User(
    id: String,
    password: String,
    name: String,
    nickname: String,
    email: String,
    profileImage: String,
    university: University,
    entranceYear: Int,
    role: Set<UserRole> = setOf(UserRole.USER)
) : UserDetails, BaseEntity() {

    /** 아이디 */
    @Id
    var id: String = id

    /** 비밀번호 */
    private var password: String = password

    /** 이름 (실명) */
    var name: String = name

    /** 닉네임 */
    var nickname: String = nickname

    /** 이메일 */
    var email: String = email

    /** 프로필 이미지 */
    var profileImage: String = profileImage

    /** 재학중인 학교 */
    @ManyToOne
    var university: University = university

    /** 입학 년도 */
    var entranceYear: Int = entranceYear

    /** 권한 */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val role: Set<UserRole> = role

    override fun getAuthorities(): MutableCollection<UserRole> {
        return role.toMutableSet()
    }

    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String, passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(password)
    }

    override fun getUsername(): String {
        return id
    }

    companion object {
        fun create(
            id: String,
            password: String,
            name: String,
            nickname: String,
            email: String,
            university: University,
            entranceYear: Int,
            passwordEncoder: PasswordEncoder,
        ): User {
            return User(
                id = id,
                password = passwordEncoder.encode(password),
                name = name,
                nickname = nickname,
                email = email,
                profileImage = "https://picsum.photos/id/100/200/200",
                university = university,
                entranceYear = entranceYear,
                role = setOf(UserRole.USER)
            )
        }
    }
}
