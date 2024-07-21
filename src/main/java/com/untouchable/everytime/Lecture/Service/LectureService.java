package com.untouchable.everytime.Lecture.Service;


import com.untouchable.everytime.Lecture.DTO.LectureDTO;
import com.untouchable.everytime.Lecture.Entity.LectureEntity;
import com.untouchable.everytime.Lecture.Repository.LectureRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final ModelMapper modelMapper;


    public LectureDTO createLecture(LectureDTO lectureDTO) {
        LectureEntity lectureEntity = modelMapper.map(lectureDTO, LectureEntity.class);
        lectureEntity = lectureRepository.save(lectureEntity);
        return modelMapper.map(lectureEntity, LectureDTO.class);
    }

    public LectureDTO updateLecture(LectureDTO lectureDTO) {
        LectureEntity lectureEntity = modelMapper.map(lectureDTO, LectureEntity.class);
        lectureEntity = lectureRepository.save(lectureEntity);
        return modelMapper.map(lectureEntity, LectureDTO.class);
    }

    public void deleteLecture(LectureDTO lectureDTO) {
        LectureEntity lectureEntity = modelMapper.map(lectureDTO, LectureEntity.class);
        lectureRepository.delete(lectureEntity);
    }

    public ArrayList<LectureDTO> getLectureList(int year, int semester, String token, Claims claims) {

        ArrayList<LectureDTO> lectureDTOArrayList = new ArrayList<>();
        ArrayList<LectureEntity> lectureEntityArrayList = (ArrayList<LectureEntity>) lectureRepository.findBySchool_SchoolNameAndYearAndSemester((String) claims.get("schoolname"), year, semester);

        for (LectureEntity lectureEntity : lectureEntityArrayList) {
            lectureDTOArrayList.add(modelMapper.map(lectureEntity, LectureDTO.class));
        }
        return lectureDTOArrayList;
    }

    public ResponseEntity<LectureDTO> getLecture(Long id) {

        Optional<LectureEntity> lectureEntity = lectureRepository.findById(id);

        if (lectureEntity.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(lectureEntity.get(), LectureDTO.class));

        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
