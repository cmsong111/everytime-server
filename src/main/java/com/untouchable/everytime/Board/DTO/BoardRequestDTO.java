package com.untouchable.everytime.Board.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "게시글 조회용 DTO")
public class BoardRequestDTO {
    @Schema(description = "게시글 종류 pk")
    private Long boardTypePK;
    @Schema(description = "게시글이름")
    private String boardTitle;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "익명 여부")
    private boolean anonymity;
    //@Schema(description = "게시글 이미지");
    //ArrayList<BoardImageDTO> images;
}
