package com.untouchable.everytime.Mail.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDTO {

    Long mail_PK;

    String reciever;
    String sender;
    String comment;
    Date createdAT;

}
