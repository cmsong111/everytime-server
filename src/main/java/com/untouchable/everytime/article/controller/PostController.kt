package com.untouchable.everytime.article.controller

import com.untouchable.everytime.article.controller.data.PostForm
import com.untouchable.everytime.article.domain.ReportType
import com.untouchable.everytime.article.service.PostService
import com.untouchable.everytime.article.service.data.PostData
import com.untouchable.everytime.article.service.data.PostSummaryData
import com.untouchable.everytime.common.domain.AuthenticatedUser
import com.untouchable.everytime.config.SwaggerConfig.BEARER_AUTH
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "5. Post", description = "게시글 관련 API (공통적으로 본인이 소속된 학교의 게시판만 조회할 수 있습니다.)")
@RestController
@RequestMapping("/v1/api/posts")
@SecurityRequirement(name = BEARER_AUTH)
class PostController(
    private val postService: PostService,
) {
    @Operation(summary = "게시글 목록 조회")
    @GetMapping("/{boardId}")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun getPost(
        @PathVariable boardId: Long,
        @ParameterObject @PageableDefault(size = 20, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): Page<PostSummaryData> {
        return postService.getPostsByBoardId(boardId, pageable)
    }

    @Operation(summary = "게시글 작성")
    @PostMapping("/{boardId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun createPost(
        @PathVariable boardId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
        @ModelAttribute postForm: PostForm,
    ): PostData {
        return postService.createPost(boardId, authenticatedUser.userId, postForm)
    }

    @Operation(summary = "게시글 수정")
    @PatchMapping("/{boardId}/{postId}")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun updatePost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
        @RequestBody form: PostForm,
    ): PostData {
        return postService.updatePost(boardId, postId, authenticatedUser.userId, form)
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{boardId}/{postId}")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun deletePost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ) {
        postService.deletePost(boardId, postId, authenticatedUser.userId)
    }


    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{boardId}/{postId}")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun getPostDetail(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): PostData {
        return postService.getPostDetail(boardId, postId)
    }

    @Operation(summary = "게시글 추천")
    @PostMapping("/{boardId}/{postId}/like")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun likePost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ) {
        postService.likePost(boardId, postId, authenticatedUser.userId)
    }

    @Operation(summary = "게시글 추천 취소")
    @DeleteMapping("/{boardId}/{postId}/like")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun unlikePost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ) {
        postService.unlikePost(boardId, postId, authenticatedUser.userId)
    }

    @Operation(summary = "게시글 신고")
    @PostMapping("/{boardId}/{postId}/report")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun reportPost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @RequestParam reportType: ReportType,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ) {
        postService.reportPost(boardId, postId, reportType, authenticatedUser.userId)
    }

    @Operation(summary = "게시글 스크랩")
    @PostMapping("/{boardId}/{postId}/scrap")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun scrapPost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ) {
        postService.scrapPost(boardId, postId, authenticatedUser.userId)
    }

    @Operation(summary = "게시글 스크랩 취소")
    @DeleteMapping("/{boardId}/{postId}/scrap")
    @PreAuthorize("@articleAccessHandler.isAccessibleBoard(#authenticatedUser, #boardId)")
    fun unScrapPost(
        @PathVariable boardId: Long,
        @PathVariable postId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ) {
        postService.unscrapPost(boardId, postId, authenticatedUser.userId)
    }
}
