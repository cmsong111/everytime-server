package com.untouchable.everytime.Board.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardScrapDTO {
    Long BoardScrapPK;
    Long board_PK;
    Long user_PK;
}
