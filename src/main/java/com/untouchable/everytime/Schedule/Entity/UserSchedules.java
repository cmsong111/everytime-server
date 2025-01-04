package com.untouchable.everytime.Schedule.Entity;

import com.untouchable.everytime.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class UserSchedules {
    @Id
    @OneToOne
    User user;

    @OneToMany
    ArrayList<UserSchedule> userSchedule;
}
