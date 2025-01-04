package com.untouchable.everytime.Schedule.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSchedule {
    @Id
    Long userSchedule_PK;

    String scheduleName;

    @OneToMany
    ArrayList<ScheduleEntity> userScheduleList;

    @OneToMany
    ArrayList<ScheduleCustomEntity> userScheduleCustomList;
}
