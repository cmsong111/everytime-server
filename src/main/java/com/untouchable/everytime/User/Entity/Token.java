package com.untouchable.everytime.User.Entity;

import com.untouchable.everytime.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Token extends BaseEntity {
    /**
     * Refresh Token
     */
    @Id
    private String refreshToken;

    /**
     * User ID
     */
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
