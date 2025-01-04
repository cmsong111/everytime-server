package com.untouchable.everytime.config

import com.untouchable.everytime.auth.TokenProvider
import com.untouchable.everytime.common.domain.AuthenticatedUser
import com.untouchable.everytime.common.domain.UnauthorizedAccessException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthenticatedUserResolver(
    private val tokenProvider: TokenProvider
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: org.springframework.core.MethodParameter): Boolean {
        // AuthenticatedUser 객체인지, AuthenticationPrincipal 어노테이션이 있는지 확인
        return parameter.parameterType == AuthenticatedUser::class.java && parameter.hasParameterAnnotation(AuthenticationPrincipal::class.java)
    }

    override fun resolveArgument(
        parameter: org.springframework.core.MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val token: String? = webRequest.getHeader("Authorization")?.substringAfter("Bearer ")

        return if (!token.isNullOrBlank()) {
            tokenProvider.decodeToken(token)
        } else {
            throw UnauthorizedAccessException()
        }
    }
}
