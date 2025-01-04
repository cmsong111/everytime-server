package com.untouchable.everytime.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes


@OpenAPIDefinition(
    info = Info(
        title = "에브리타입 클론 코딩 프로젝트 API",
        version = "v1",
        description = "에브리타입 클론 코딩 프로젝트 API 문서입니다."
    ),
)
@SecuritySchemes(
    SecurityScheme(
        name = SwaggerConfig.BEARER_AUTH,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
    )
)
object SwaggerConfig {
    const val BEARER_AUTH: String = "Bearer Authentication"
}
