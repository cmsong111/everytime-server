package com.untouchable.everytime.User.Entity;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.User.Enum.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;
    @ManyToOne
    private School userSchool;
    private int userRegisteredYear;
    // @OneToOne
    // private UserProfileEntity userProfile;
    private boolean userSchoolVerified;
    Long userPoint;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}
