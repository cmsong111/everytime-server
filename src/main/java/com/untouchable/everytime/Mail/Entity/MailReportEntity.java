package com.untouchable.everytime.Mail.Entity;


import com.untouchable.everytime.article.domain.ReportType;
import com.untouchable.everytime.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MailReportEntity {

    @Id
    @OneToOne
    private MailRoomEntity mailRoom;

    @Enumerated(EnumType.STRING)
    ReportType reportType;

    @ManyToOne
    User reportUser;

    @ManyToOne
    User reportedUser;

}
