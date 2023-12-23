package com.untouchable.everytime.School.Service;

import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import com.untouchable.everytime.School.DTO.SchoolListDTO;
import com.untouchable.everytime.School.Entity.School;
import com.untouchable.everytime.School.Repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchoolService {

    SchoolRepository schoolRepository;
    ModelMapper modelMapper;

    public ResponseEntity<SchoolInfoDTO> createSchool(SchoolInfoDTO schoolInfoDTO) {
        School school = modelMapper.map(schoolInfoDTO, School.class);
        if (schoolRepository.existsById(school.getSchoolName())) {
            return ResponseEntity.badRequest().build();
        }
        schoolRepository.save(school);
        return ResponseEntity.ok(modelMapper.map(school, SchoolInfoDTO.class));
    }

    public ResponseEntity<SchoolInfoDTO> getSchool(String id) {
        Optional<School> schoolEntity = schoolRepository.findById(id);
        if (schoolEntity.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(schoolEntity, SchoolInfoDTO.class));
        }
        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<SchoolInfoDTO> updateSchool(SchoolInfoDTO schoolInfoDTO) {
        if (!schoolRepository.existsById(schoolInfoDTO.getSchoolName())) {
            return ResponseEntity.badRequest().build();
        }
        School school = modelMapper.map(schoolInfoDTO, School.class);
        schoolRepository.save(school);
        return ResponseEntity.ok(modelMapper.map(school, SchoolInfoDTO.class));
    }

    public void deleteSchool(String id) {
        schoolRepository.deleteById(id);
    }

    /**
     * 회원가입 시 모든 학교 정보 가져오는 기능
     * 
     * @return 학교명 전부
     * @author 김남주
     */
    public ResponseEntity<ArrayList<SchoolListDTO>> findAllSchool() {
        List<School> schoolEntities = schoolRepository.findAll();
        ArrayList<SchoolListDTO> schoolInfoDTOS = new ArrayList<>();
        for (School school : schoolEntities) {
            schoolInfoDTOS.add(modelMapper.map(school, SchoolListDTO.class));
        }
        return ResponseEntity.ok(schoolInfoDTOS);

    }
}
