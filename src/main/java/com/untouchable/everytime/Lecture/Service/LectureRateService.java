package com.untouchable.everytime.Lecture.Service;

import com.untouchable.everytime.Lecture.DTO.LectureRateDTO;
import com.untouchable.everytime.Lecture.Entity.LectureRateEntity;
import com.untouchable.everytime.Lecture.Repository.LectureRateRepository;
import com.untouchable.everytime.Lecture.Repository.LectureRepository;
import com.untouchable.everytime.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureRateService {

    private final LectureRateRepository lectureRateRepository;
    private final LectureRepository lectureRepository;
    private final UserJpaRepository userRepository;
    private final ModelMapper modelMapper;


    public ResponseEntity<LectureRateDTO> createLectureRate(LectureRateDTO lectureRateDTO, String token) {
        // 토큰 검증


        //LectureEntity 만들기
        LectureRateEntity lectureRateEntity = modelMapper.map(lectureRateDTO, LectureRateEntity.class);
        lectureRateEntity.setUser(userRepository.findById(token).get());
        lectureRateEntity.setLecture(lectureRepository.findById(lectureRateDTO.getLectureLecturePK()).get());

        // lecture 저장
        lectureRateEntity = lectureRateRepository.save(lectureRateEntity);
        return ResponseEntity.ok(modelMapper.map(lectureRateEntity, LectureRateDTO.class));
    }
}
