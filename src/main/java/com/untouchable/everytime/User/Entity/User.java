package com.untouchable.everytime.User.Entity;

import com.untouchable.everytime.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    /**
     * User ID
     */
    @Id
    private String id;

    /**
     * User Password
     */
    private String password;

    /**
     * User Real Name
     */
    private String name;

    /**
     * User Nickname
     */
    private String nickname;

    /**
     * User Email
     */
    @Column(unique = true)
    private String email;

    /**
     * User Profile Image
     */
    @Column(columnDefinition = "TEXT")
    private String profileImage;

    /**
     * User Role
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    /**
     * Security에서 사용하는 사용자 이름을 반환한다.
     */
    @Override
    public String getUsername() {
        return this.id;
    }
}
