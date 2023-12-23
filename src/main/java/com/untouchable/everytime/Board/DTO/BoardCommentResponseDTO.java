package com.untouchable.everytime.Board.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommentResponseDTO {
    private Long boardCommentPK;
    Long boardBoardPK;
    String userUserID;
    String userUserNickname;
    boolean anonymity;
    Timestamp createdAT;
    Long reportCount;
    Long recommendCount;
    String content;
    Long replyTo;
}

