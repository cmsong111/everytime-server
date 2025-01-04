package com.untouchable.everytime.university.controller

import com.untouchable.everytime.config.SwaggerConfig.BEARER_AUTH
import com.untouchable.everytime.university.controller.data.UniversityCreateForm
import com.untouchable.everytime.university.controller.data.UniversitySummary
import com.untouchable.everytime.university.service.UniversityService
import com.untouchable.everytime.university.service.data.UniversityData
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "3. University", description = "대학 정보 관리")
@RestController
@RequestMapping("/v1/api/universities")
class UniversityController(
    private val universityService: UniversityService
) {
    @GetMapping
    fun getUniversities(
        @ParameterObject @PageableDefault(size = 20, sort = ["name"]) pageable: Pageable,
        @RequestParam(required = false) name: String?,
    ): Page<UniversitySummary> {
        return universityService.findUniversity(name, pageable).map { university ->
            UniversitySummary.from(university)
        }
    }

    @GetMapping("/{universityId}")
    @Operation(summary = "대학 정보 조회")
    @SecurityRequirement(name = BEARER_AUTH)
    fun getUniversity(
        @PathVariable universityId: Long,
    ): UniversityData {
        return universityService.findUniversityById(universityId)
    }

    @PostMapping
    @Operation(summary = "대학 생성")
    @SecurityRequirement(name = BEARER_AUTH)
    @Secured("ROLE_ADMIN")
    fun createUniversity(
        @RequestBody universityCreateForm: UniversityCreateForm
    ): UniversityData {
        return universityService.createUniversity(universityCreateForm)
    }

    @DeleteMapping("/{universityId}")
    @Operation(summary = "대학 삭제", description = "대학을 삭제합니다. 신중히 결정하세요.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = BEARER_AUTH)
    @Secured("ROLE_ADMIN")
    fun deleteUniversity(
        @PathVariable universityId: Long,
    ) {
        universityService.deleteUniversity(universityId)
    }
}
