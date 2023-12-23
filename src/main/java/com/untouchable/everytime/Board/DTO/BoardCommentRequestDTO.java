package com.untouchable.everytime.Board.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCommentRequestDTO {
    Long boardBoardPK;
    boolean anonymity;
    String content;
    Long replyTo;

}
