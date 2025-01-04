package com.untouchable.everytime.Schedule.Entity;

import com.untouchable.everytime.Lecture.Entity.LectureEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulePK;

    @ManyToOne
    LectureEntity lecture;

    String location;

    // 일요일 0 ~ 토요일 6
    @Column(name = "Schedule_Day")
    int day;

    // 0시 ~ 24시 , start < end
    @Column(name = "start_time")
    int start;
    @Column(name = "end_time")
    int end;
}
