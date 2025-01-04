package com.untouchable.everytime.article.domain

/**
 * 신고 타입
 * @property description 신고 타입 설명
 * @constructor 신고 타입 생성자
 * @property INAPPROPRIATE_CONTENT 게시판 성격에 부적절함
 * @property PROFANITY 욕설/비하
 * @property EXPLICIT_CONTENT 음란물/불건전한 만남 및 대화
 * @property COMMERCIAL_ADVERTISING 상업적 광고 및 판매
 * @property LEAKING_IMPERSONATION_FRAUD 유출/사칭/사기
 * @property SPAM_SCAM_SHOCK 낚시/놀람/도배
 * @property POLITICAL_DEFAMATION_CAMPAIGNING 정당/정치인 비하 및 선거운동
 * @property ILLEGAL_MEDIA_DISTRIBUTION 불법촬영물 등의 유통
 */
enum class ReportType(
    val description: String
) {
    INAPPROPRIATE_CONTENT("게시판 성격에 부적절함"),
    PROFANITY("욕설/비하"),
    EXPLICIT_CONTENT("음란물/불건전한 만남 및 대화"),
    COMMERCIAL_ADVERTISING("상업적 광고 및 판매"),
    LEAKING_IMPERSONATION_FRAUD("유출/사칭/사기"),
    SPAM_SCAM_SHOCK("낚시/놀람/도배"),
    POLITICAL_DEFAMATION_CAMPAIGNING("정당/정치인 비하 및 선거운동"),
    ILLEGAL_MEDIA_DISTRIBUTION("불법촬영물 등의 유통")
}
