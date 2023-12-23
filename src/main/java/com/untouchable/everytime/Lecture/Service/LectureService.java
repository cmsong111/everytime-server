package com.untouchable.everytime.Lecture.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.Lecture.DTO.LectureDTO;
import com.untouchable.everytime.Lecture.Entity.LectureEntity;
import com.untouchable.everytime.Lecture.Repository.LectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class LectureService {

    LectureRepository lectureRepository;
    ModelMapper modelMapper;
    JwtConfig jwtConfig;

    @Autowired
    public LectureService(JwtConfig jwtConfig, LectureRepository lectureRepository, ModelMapper strictMapper) {
        this.lectureRepository = lectureRepository;
        this.modelMapper = strictMapper;
        this.jwtConfig = jwtConfig;
    }

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

    public ArrayList<LectureDTO> getLectureList(int year, int semester, String token) {
        Map<String, Object> jwt = jwtConfig.verifyJWT(token);

        ArrayList<LectureDTO> lectureDTOArrayList = new ArrayList<>();
        ArrayList<LectureEntity> lectureEntityArrayList = (ArrayList<LectureEntity>) lectureRepository.findBySchool_SchoolNameAndYearAndSemester(String.valueOf(jwt.get("SCHOOL")), year, semester);

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
