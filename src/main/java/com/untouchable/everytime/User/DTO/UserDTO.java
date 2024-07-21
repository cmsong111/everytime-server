package com.untouchable.everytime.User.DTO;

import com.untouchable.everytime.User.Enum.UserStatus;
import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userSchoolSchoolName;
    private int userRegisteredYear;
    private String userProfile;
    private boolean userSchoolVerified;
    Long userPoint;
    private UserStatus userStatus;
}
