package com.untouchable.everytime.Lecture.Controller;

import com.untouchable.everytime.Lecture.DTO.LectureRateDTO;
import com.untouchable.everytime.Lecture.Service.LectureRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectureRate")
@Tag(name = "강의 평가", description = "강의 평가 CRUD API")
public class LectureRateController {

    LectureRateService lectureRateService;

    @Autowired
    public LectureRateController(LectureRateService lectureRateService) {
        this.lectureRateService = lectureRateService;
    }

    @PostMapping("/create")
    @Operation(summary = "강의 평가 생성", description = "강의 평가 생성하는 API")
    public ResponseEntity<LectureRateDTO> createLectureRate(
            @RequestBody LectureRateDTO lectureRateDTO,
            @Parameter(name = "jwt", description = "유저 인증 토큰") @RequestHeader(value = "jwt") String token
    ) {
        return lectureRateService.createLectureRate(lectureRateDTO, token);
    }
}
