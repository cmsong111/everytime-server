package com.untouchable.everytime.article.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


/**
 * 게시글 이미지 엔티티
 * @property url 이미지 URL
 * @property order 이미지 순서
 */
@Embeddable
data class Image(
    val url: String,
    @Column(name = "image_order")
    val order: Int,
)
