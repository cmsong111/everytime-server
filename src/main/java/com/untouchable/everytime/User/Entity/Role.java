package com.untouchable.everytime.User.Entity;


import org.springframework.security.core.GrantedAuthority;

/**
 * 사용자 권한
 */
public enum Role implements GrantedAuthority {
    /**
     * 일반 사용자
     */
    USER,
    /**
     * 관리자
     */
    ADMIN,
    /**
     * 대학교 관리자
     */
    MANAGER,
    ;

    /**
     * Spring Security에서 사용하는 권한 문자열을 반환한다.
     * @return 권한이름
     */
    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
