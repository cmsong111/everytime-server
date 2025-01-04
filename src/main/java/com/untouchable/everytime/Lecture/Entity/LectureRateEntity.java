package com.untouchable.everytime.Lecture.Entity;

import com.untouchable.everytime.common.Enum.LectureStatus;
import com.untouchable.everytime.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LectureRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LPK;

    @ManyToOne
    LectureEntity lecture;

    String content;
    int recommendCount;
    Long rate;

    // 10 1학기, 11 하계 계절학기, 20 2학기, 21 동계 계절학기
    @Column(name = "Lecture_Year")
    int year;
    int semester;

    @Enumerated(EnumType.STRING)
    LectureStatus assignment;
    @Enumerated(EnumType.STRING)
    LectureStatus teamMeeting;
    @Enumerated(EnumType.STRING)
    LectureStatus score;


    @ManyToOne
    User user;

}
