package com.untouchable.everytime.Board.Controller;

import com.untouchable.everytime.Board.DTO.BoardTypeDTO;
import com.untouchable.everytime.Board.Service.BoardTypeService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Tag(name = "게시글 종류 CRUD", description = "게시글 종류 CRUD 관련 API")
@RestController
@AllArgsConstructor
@RequestMapping("/boardType")
public class BoardTypeController {

    BoardTypeService boardTypeService;


    @PostMapping("/create")
    public BoardTypeDTO createBoardType(@RequestBody BoardTypeDTO boardTypeDTO) {
        return boardTypeService.createBoardType(boardTypeDTO);
    }

    @GetMapping("/getBoardTypeBySchoolName")
    public ResponseEntity<ArrayList<BoardTypeDTO>> getBoardType(
            Principal principal, Claims claims) {

        return boardTypeService.getBoardTypeBySchoolName(principal.getName(), claims);
    }

    @PatchMapping("/update")
    public BoardTypeDTO updateBoardType(@RequestBody BoardTypeDTO boardTypeDTO) {
        return boardTypeService.createBoardType(boardTypeDTO);
    }

    @DeleteMapping("/delete")
    public void deleteBoardType(@RequestBody BoardTypeDTO boardTypeDTO) {
        boardTypeService.deleteBoardType(boardTypeDTO.getBoardTypePK());
    }


}
