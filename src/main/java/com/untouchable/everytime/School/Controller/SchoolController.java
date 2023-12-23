package com.untouchable.everytime.School.Controller;

import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import com.untouchable.everytime.School.DTO.SchoolListDTO;
import com.untouchable.everytime.School.Service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "학교", description = "학교 CRUD API")
@RestController
@AllArgsConstructor
@RequestMapping("/school")
public class SchoolController {

    SchoolService schoolService;

    @PostMapping("/create")
    @Operation(summary = "학교 생성", description = "학교 생성하는 API")
    public ResponseEntity<SchoolInfoDTO> createSchool(@RequestBody SchoolInfoDTO schoolInfoDTO) {
        return schoolService.createSchool(schoolInfoDTO);
    }

    @GetMapping("/get")
    @Operation(summary = "학교 정보 조회", description = "학교 Info 요청하는 기능")
    public ResponseEntity<SchoolInfoDTO> getSchool(
            @Parameter(name = "대학명", description = "대학교 이름") @RequestParam("id") String id) {
        return schoolService.getSchool(id);

    }

    @PatchMapping("/update")
    @Operation(summary = "학교 정보 수정", description = "학교 정보 수정하는 기능")
    public ResponseEntity<SchoolInfoDTO> updateSchool(@RequestBody SchoolInfoDTO schoolInfoDTO) {
        return schoolService.updateSchool(schoolInfoDTO);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "학교 삭제하는 기능", description = "특정 학교를 삭제하는 기능")
    public ResponseEntity deleteSchool(
            @Parameter(name = "대학명", description = "대학교 이름") @RequestParam("id") String id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/findAll")
    @Operation(summary = "학교명 전부 요청하는 기능", description = "회원가입 시 모든 학교 정보 가져오는 기능")
    public ResponseEntity<ArrayList<SchoolListDTO>> findAllSchool() {
        return schoolService.findAllSchool();
    }


}
