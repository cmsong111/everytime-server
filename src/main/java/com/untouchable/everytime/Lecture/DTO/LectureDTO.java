package com.untouchable.everytime.Lecture.DTO;

import com.untouchable.everytime.Enum.LectureType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureDTO {
    @Schema(description = "강의 PK")
    Long lecturePK;
    @Schema(description = "강의 번호")
    LectureType lectureType;
    @Schema(description = "학교")
    String schoolName;
    @Schema(description = "강의 번호")
    int lectureNum;
    @Schema(description = "강의 분반")
    int separation;
    @Schema(description = "강의명")
    String lectureName;
    @Schema(description = "학점")
    int credit;
    @Schema(description = "수강 학년")
    int grade;
    @Schema(description = "교수(소속)")
    String professor;
    @Schema(description = "평점")
    Long average;
    @Schema(description = "년도")
    int year;
    @Schema(description = "학기 설명 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기")
    int semester;

    //ArrayList<String> book_PK;

    //Long schedule_PK;

}
