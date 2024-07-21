package com.untouchable.everytime.Config;

import com.untouchable.everytime.common.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");

        // 토큰이 존재하고, Bearer로 시작하는 경우에만 필터를 통과시킨다.
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token 검증 & User 정보 가져오기
        String userId = jwtService.extractUsername(token.substring(7));
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        // SecurityContext에 User 정보 저장

        // UsernamePasswordAuthenticationToken: AuthenticationManager가 처리할 수 있는 토큰
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        // WebAuthenticationDetails: IP, 세션 ID 등의 추가 정보를 저장
        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // SecurityContextHolder: SecurityContext를 제공하는 클래스
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
