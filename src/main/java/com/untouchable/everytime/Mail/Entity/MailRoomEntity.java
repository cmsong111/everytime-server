package com.untouchable.everytime.Mail.Entity;

import com.untouchable.everytime.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class MailRoomEntity {
    @Id
    private Long mailRoom_PK;
    boolean anonymity;
    @ManyToOne
    User user1;
    @ManyToOne
    User user2;
    @OneToMany
    ArrayList<MailEntity> mailList;

}
