package com.untouchable.everytime.article.controller.data

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

/**
 * 게시글 작성 폼
 * @property isAnonymous 익명 여부
 * @property title 제목
 * @property content 내용
 * @property images 이미지 파일
 */
@Schema(description = "게시글 작성 폼")
data class PostForm(
    @Schema(description = "익명 여부")
    val isAnonymous: Boolean,
    @Schema(description = "제목", example = "제목")
    val title: String,
    @Schema(description = "내용", example = "내용")
    val content: String,
    @Schema(description = "이미지 파일", required = false)
    val images: List<MultipartFile>? = null,
)
