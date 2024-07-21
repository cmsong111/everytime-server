package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.DTO.BoardTypeDTO;
import com.untouchable.everytime.Board.Entity.BoardType;
import com.untouchable.everytime.Board.Repository.BoardTypeRepository;

import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardTypeService {

    BoardTypeRepository boardTypeRepository;
    ModelMapper modelMapper;
    SchoolRepository schoolRepository;


    public BoardTypeDTO createBoardType(BoardTypeDTO boardTypeDTO) {
        BoardType boardType = modelMapper.map(boardTypeDTO, BoardType.class);
        boardType.setSchool(schoolRepository.findById(boardTypeDTO.getSchoolName()).get());
        BoardType result = boardTypeRepository.save(boardType);
        return modelMapper.map(result, BoardTypeDTO.class);
    }

    public BoardTypeDTO updateBoardType(BoardTypeDTO boardTypeDTO) {
        BoardType boardType = modelMapper.map(boardTypeDTO, BoardType.class);
        BoardType result = boardTypeRepository.save(boardType);
        return modelMapper.map(result, BoardTypeDTO.class);
    }

    public void deleteBoardType(Long id) {
        boardTypeRepository.deleteById(id);
    }

    public ResponseEntity<ArrayList<BoardTypeDTO>> getBoardTypeBySchoolName(String username, Claims claims) {

        Optional<School> school = schoolRepository.findById(claims.get("schoolName").toString());
        // 학교가 Null 일 때
        if (school.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<BoardType> boardType = boardTypeRepository.findBySchool(school.get());

        ArrayList<BoardTypeDTO> boardTypeDTOS = new ArrayList<>();
        for (BoardType boardType1 : boardType) {
            boardTypeDTOS.add(modelMapper.map(boardType1, BoardTypeDTO.class));
        }

        return ResponseEntity.ok(boardTypeDTOS);
    }


}
