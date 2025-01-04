package com.untouchable.everytime.university.domain

/**
 * 대학 링크 타입 (에브리타임에서 기본적으로 제공하는 링크 타입)
 * @property description 링크 타입 설명 (한글)
 * @property HOME 대표 홈페이지
 * @property STUDY_ROOM 스터디룸
 * @property SHUTTLE 셔틀버스
 * @property NOTICE 공지사항
 * @property CALENDAR 학사일정
 * @property LIBRARY 도서관
 */
enum class UniversityLinkType(
    val description: String
) {
    HOME("대표 홈페이지"),
    STUDY_ROOM("스터디룸"),
    SHUTTLE("셔틀버스"),
    NOTICE("공지사항"),
    CALENDAR("학사일정"),
    LIBRARY("도서관")
}
