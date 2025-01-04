package com.untouchable.everytime.article.domain

/**
 * 게시판 타입
 * @property DEFAULT 기본 게시판 (자유게시판, 비밀 게시판 등)
 * @property CAREER 취업 게시판 (취업.진로)
 * @property MAJOR 전공 게시판 (간호학과, 컴퓨터공학과 등)
 * @property ETC 기타 게시판 (유저들이 만든 게시판)
 */
enum class BoardType {
    DEFAULT,
    CAREER,
    MAJOR,
    ETC,
}
