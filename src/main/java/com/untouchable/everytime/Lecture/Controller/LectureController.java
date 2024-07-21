package com.untouchable.everytime.Lecture.Controller;

import com.untouchable.everytime.Lecture.DTO.LectureDTO;
import com.untouchable.everytime.Lecture.Service.LectureService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

//@Tag(name = "Lecture")
@RestController
@RequestMapping("/lecture")
@Tag(name = "강의 관련 API")
public class LectureController {

    LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }


    @PostMapping("/create")
    @Operation(summary = "강의 생성", description = "강의 생성하는 API ")
    public LectureDTO createLecture(@RequestBody LectureDTO lectureDTO) {
        return lectureService.createLecture(lectureDTO);
    }

    @Operation(summary = "강의 수정", description = "강의 업데이트하는 API ")
    @PatchMapping("/update")
    public LectureDTO updateLecture(@RequestBody LectureDTO lectureDTO) {
        return lectureService.updateLecture(lectureDTO);
    }

    @DeleteMapping("/delete")
    @Operation(description = "강의 삭제하는 API ")
    public void deleteLecture(@RequestBody LectureDTO lectureDTO) {
        lectureService.deleteLecture(lectureDTO);
    }

    @Operation(description = "강의 조회하는 API 입니다.")
    @GetMapping("/getList")
    public ArrayList<LectureDTO> getLecture(
            @RequestParam("year") int year,
            @RequestParam("semester") int semester,
            Principal principal,
            Claims claims) {
        return lectureService.getLectureList(year, semester, principal.getName(), claims);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LectureDTO> getLecture(@PathVariable("id") Long id) {
        return lectureService.getLecture(id);
    }


}
