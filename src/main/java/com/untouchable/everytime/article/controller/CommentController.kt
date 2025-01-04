package com.untouchable.everytime.article.controller

import com.untouchable.everytime.article.controller.data.CommentForm
import com.untouchable.everytime.article.domain.ReportType
import com.untouchable.everytime.article.service.CommentService
import com.untouchable.everytime.common.domain.AuthenticatedUser
import com.untouchable.everytime.config.SwaggerConfig.BEARER_AUTH
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "6. Comment", description = "댓글")
@RestController
@RequestMapping("/v1/api/comment")
@SecurityRequirement(name = BEARER_AUTH)
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping
    @Operation(summary = "댓글 작성", description = "댓글 작성")
    fun createComment(
        @RequestBody commentForm: CommentForm,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<Unit> {
        commentService.createComment(
            commentForm = commentForm,
            userId = authenticatedUser.userId
        )
        return ResponseEntity.created(URI("/v1/api/post/${commentForm.boardId}/${commentForm.postId}")).build()
    }

    @PatchMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "댓글 수정")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody commentForm: CommentForm,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<Unit> {
        commentService.updateComment(
            commentId = commentId,
            commentForm = commentForm,
            userId = authenticatedUser.userId
        )
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제")
    fun deleteComment(
        @PathVariable commentId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<Unit> {
        commentService.deleteComment(
            commentId = commentId,
            userId = authenticatedUser.userId
        )
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{commentId}/like")
    @Operation(summary = "댓글 좋아요", description = "댓글 좋아요. 좋아요 수 반환")
    fun likeComment(
        @PathVariable commentId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<Long> {
        return ResponseEntity.ok(
            commentService.likeComment(
                commentId = commentId,
                userId = authenticatedUser.userId
            )
        )
    }

    @DeleteMapping("/{commentId}/like")
    @Operation(summary = "댓글 좋아요 취소", description = "댓글 좋아요 취소")
    fun unlikeComment(
        @PathVariable commentId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<Long> {
        return ResponseEntity.ok(
            commentService.unlikeComment(
                commentId = commentId,
                userId = authenticatedUser.userId
            )
        )
    }

    @PostMapping("/{commentId}/report")
    @Operation(summary = "댓글 신고", description = "댓글 신고")
    fun reportComment(
        @PathVariable commentId: Long,
        @RequestParam reason: ReportType,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser,
    ): ResponseEntity<Unit> {
        val result: Boolean = commentService.reportComment(
            commentId = commentId,
            reportType = reason,
            userId = authenticatedUser.userId
        )
        return if (result) {
            ResponseEntity.created(URI("")).build()
        } else {
            ResponseEntity.accepted().build()
        }
    }

    @PostMapping("/{commentId}/reply")
    @Operation(summary = "댓글 답글", description = "댓글 답글")
    fun replyComment() {

    }
}
