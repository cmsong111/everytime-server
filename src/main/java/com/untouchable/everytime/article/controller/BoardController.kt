package com.untouchable.everytime.article.controller

import com.untouchable.everytime.article.service.BoardService
import com.untouchable.everytime.article.service.data.BoardData
import com.untouchable.everytime.common.domain.AuthenticatedUser
import com.untouchable.everytime.config.SwaggerConfig.BEARER_AUTH
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "4. Board", description = "게시판 API")
@RestController
@RequestMapping("/v1/api/boards")
@SecurityRequirement(name = BEARER_AUTH)
class BoardController(
    private val boardService: BoardService,
) {
    @GetMapping
    @Operation(summary = "게시판 목록 조회", description = "사용자가 속한 대학교의 게시판 목록을 조회합니다.")
    fun getBoards(
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ): List<BoardData> {
        return boardService.getBoardsByUniversityId(authenticatedUser.universityId)
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @Operation(summary = "게시판 생성", description = "(미개발) 게시판을 생성합니다.", deprecated = true)
    fun createBoard(
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ): BoardData {
        TODO("Not yet implemented")
    }

    @DeleteMapping("/{boardId}")
    @Secured("ROLE_ADMIN")
    @Operation(summary = "게시판 삭제", description = "(미개발) 게시판을 삭제합니다.", deprecated = true)
    fun deleteBoard(
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ) {
        TODO("Not yet implemented")
    }

    @PatchMapping("/{boardId}")
    @Secured("ROLE_ADMIN")
    @Operation(summary = "게시판 수정", description = "(미개발) 게시판을 수정합니다.", deprecated = true)
    fun updateBoard(
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ): BoardData {
        TODO("Not yet implemented")
    }
}
