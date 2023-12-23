package com.untouchable.everytime.Board.DTO;

import com.untouchable.everytime.Enum.BoardTypeClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardTypeDTO {
    private Long boardTypePK;
    String schoolName;
    String boardType;
    String boardDescription;
    BoardTypeClass boardTypeClass;
}
