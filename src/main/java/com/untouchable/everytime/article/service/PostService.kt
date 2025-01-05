package com.untouchable.everytime.article.service

import com.untouchable.everytime.article.controller.data.PostForm
import com.untouchable.everytime.article.domain.Post
import com.untouchable.everytime.article.domain.Report
import com.untouchable.everytime.article.domain.ReportType
import com.untouchable.everytime.article.repository.BoardJpaRepository
import com.untouchable.everytime.article.repository.PostJpaRepository
import com.untouchable.everytime.article.service.data.PostData
import com.untouchable.everytime.article.service.data.PostSummaryData
import com.untouchable.everytime.common.domain.PostNotFoundException
import com.untouchable.everytime.common.domain.UserNotFoundException
import com.untouchable.everytime.common.storage.StorageService
import com.untouchable.everytime.user.repository.UserJpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val boardJpaRepository: BoardJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val storageService: StorageService,
) {

    /**
     * 게시판 ID로 게시글 목록 조회
     * @param boardId 게시판 ID
     * @return 게시글 목록
     */
    @Transactional(readOnly = true)
    fun getPostsByBoardId(boardId: Long, pageable: Pageable): Page<PostSummaryData> {
        return postJpaRepository.findByBoardId(
            boardId = boardId,
            pageable = pageable,
        ).map {
            PostSummaryData.from(it)
        }
    }

    /**
     * 게시글 작성
     * @param boardId 게시판 ID
     * @param userId 사용자 ID
     * @param postForm 게시글 작성 폼
     * @return 게시글 ID
     */
    @Transactional
    fun createPost(boardId: Long, userId: String, postForm: PostForm): PostData {
        return PostData.from(
            postJpaRepository.save(
                Post.create(
                    board = boardJpaRepository.findByIdOrNull(boardId) ?: throw PostNotFoundException(),
                    author = userJpaRepository.findByIdOrNull(userId) ?: throw UserNotFoundException(),
                    postForm = postForm,
                    images = postForm.images?.let { storageService.saveAll(it) } ?: emptyList()
                )
            )
        )
    }

    /**
     * 게시글 수정
     * @param boardId 게시판 ID
     * @param postId 게시글 ID
     * @param postForm 게시글 수정 폼
     * @return 게시글 ID
     */
    @Transactional
    fun updatePost(boardId: Long, postId: Long, userId: String, postForm: PostForm): PostData {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        require(post.author.id == userId) { "User is not author" }
        post.update(postForm)
        return PostData.from(post)
    }

    /**
     * 게시글 삭제
     * @param boardId 게시판 ID
     * @param postId 게시글 ID
     */
    @Transactional
    fun deletePost(boardId: Long, postId: Long, userId: String) {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        require(post.author.id == userId) { "User is not author" }
        postJpaRepository.delete(post)
    }

    /**
     * 게시글 좋아요
     */
    @Transactional
    fun likePost(boardId: Long, postId: Long, userId: String) {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        post.likes.add(userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("User not found"))
        post.likeCount = post.likes.size.toLong()
    }

    /**
     * 게시글 좋아요 취소
     */
    @Transactional
    fun unlikePost(boardId: Long, postId: Long, userId: String) {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        post.likes.remove(userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("User not found"))
        post.likeCount = post.likes.size.toLong()
    }

    /**
     * 게시글 스크랩
     */
    @Transactional
    fun scrapPost(boardId: Long, postId: Long, userId: String) {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        post.scraps.add(userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("User not found"))
        post.scrapCount = post.scraps.size.toLong()
    }

    /**
     * 게시글 스크랩 취소
     */
    @Transactional
    fun unscrapPost(boardId: Long, postId: Long, userId: String) {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        post.scraps.remove(userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("User not found"))
        post.scrapCount = post.scraps.size.toLong()
    }

    /**
     * 게시글 신고
     */
    @Transactional
    fun reportPost(boardId: Long, postId: Long, reportType: ReportType, userId: String) {
        val post = postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        post.reports.add(
            Report(
                type = reportType,
                reporter = userJpaRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("User not found"),
            )
        )
    }

    /**
     * 게시글 ID로 게시글 조회
     * @param boardId 게시판 ID
     * @param postId 게시글 ID
     * @return 게시글
     */
    @Transactional(readOnly = true)
    fun getPostDetail(boardId: Long, postId: Long): PostData {
        return PostData.from(
            postJpaRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Post not found")
        )
    }
}
