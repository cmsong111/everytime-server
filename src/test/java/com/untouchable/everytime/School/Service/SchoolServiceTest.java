package com.untouchable.everytime.School.Service;

import com.untouchable.everytime.School.DTO.SchoolInfoDTO;
import com.untouchable.everytime.School.DTO.SchoolListDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SchoolServiceTest {

    @Autowired
    private SchoolService schoolService;


//    @Test
//    @DisplayName("학교 생성 성공")
//    @Transactional
//    void createSchool() {
//        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
//                .schoolName("테스트 대학교")
//                .schoolLocation("대한민국 어딘가")
//                .schoolTell("010-1111-2222")
//                .schoolFax("010-2222-3333")
//                .schoolUrlHome("https://www.test.com")
//                .schoolUrlStudyRoom("https://www.test.com")
//                .schoolUrlShuttle("https://www.test.com")
//                .schoolUrlNotice("https://www.test.com")
//                .schoolUrlCalendar("https://www.test.com")
//                .schoolUrlLibrary("https://www.test.com")
//                .build();
//        SchoolInfoDTO result = schoolService.createSchool(schoolInfoDTO).getBody();
//
//        assertEquals(result.getSchoolName(), schoolInfoDTO.getSchoolName());
//    }
//
//    @Test
//    @DisplayName("학교 생성 실패")
//    @Transactional
//    void createSchoolFail() {
//        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
//                .schoolName("테스트 대학교")
//                .schoolLocation("대한민국 어딘가")
//                .schoolTell("010-1111-2222")
//                .schoolFax("010-2222-3333")
//                .schoolUrlHome("https://www.test.com")
//                .schoolUrlStudyRoom("https://www.test.com")
//                .schoolUrlShuttle("https://www.test.com")
//                .schoolUrlNotice("https://www.test.com")
//                .schoolUrlCalendar("https://www.test.com")
//                .schoolUrlLibrary("https://www.test.com")
//                .build();
//
//        SchoolInfoDTO result1 = schoolService.createSchool(schoolInfoDTO).getBody();
//        SchoolInfoDTO result2 = schoolService.createSchool(schoolInfoDTO).getBody();
//
//        assertEquals(result1, schoolInfoDTO);
//        assertNotEquals(result2, schoolInfoDTO);
//    }
//
//
//    @Test
//    @DisplayName("학교 조회")
//    @Transactional
//    void getSchool() {
//        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
//                .schoolName("테스트 대학교")
//                .schoolLocation("대한민국 어딘가")
//                .schoolTell("010-1111-2222")
//                .schoolFax("010-2222-3333")
//                .schoolUrlHome("https://www.test.com")
//                .schoolUrlStudyRoom("https://www.test.com")
//                .schoolUrlShuttle("https://www.test.com")
//                .schoolUrlNotice("https://www.test.com")
//                .schoolUrlCalendar("https://www.test.com")
//                .schoolUrlLibrary("https://www.test.com")
//                .build();
//
//        SchoolInfoDTO result1 = schoolService.createSchool(schoolInfoDTO).getBody();
//
//        schoolService.getSchool(result1.getSchoolName());
//
//        assertEquals(result1, schoolInfoDTO);
//    }
//
//    @Test
//    @DisplayName("학교 업데이트")
//    @Transactional
//    void updateSchool() {
//        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
//                .schoolName("테스트 대학교")
//                .schoolLocation("대한민국 어딘가")
//                .schoolTell("010-1111-2222")
//                .schoolFax("010-2222-3333")
//                .schoolUrlHome("https://www.test.com")
//                .schoolUrlStudyRoom("https://www.test.com")
//                .schoolUrlShuttle("https://www.test.com")
//                .schoolUrlNotice("https://www.test.com")
//                .schoolUrlCalendar("https://www.test.com")
//                .schoolUrlLibrary("https://www.test.com")
//                .build();
//
//        SchoolInfoDTO result1 = schoolService.createSchool(schoolInfoDTO).getBody();
//
//        result1.setSchoolLocation("대한민국 어딘가2");
//        result1.setSchoolTell("010-1111-3333");
//
//        SchoolInfoDTO result2 = schoolService.updateSchool(result1).getBody();
//
//        assertEquals(result2.getSchoolName(), result1.getSchoolName());
//        assertEquals(result2.getSchoolLocation(), result1.getSchoolLocation());
//        assertEquals(result2.getSchoolTell(), result1.getSchoolTell());
//
//    }
//
//    @Test
//    @DisplayName("학교 삭제")
//    @Transactional
//    void deleteSchool() {
//        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
//                .schoolName("테스트 대학교")
//                .schoolLocation("대한민국 어딘가")
//                .schoolTell("010-1111-2222")
//                .schoolFax("010-2222-3333")
//                .schoolUrlHome("https://www.test.com")
//                .schoolUrlStudyRoom("https://www.test.com")
//                .schoolUrlShuttle("https://www.test.com")
//                .schoolUrlNotice("https://www.test.com")
//                .schoolUrlCalendar("https://www.test.com")
//                .schoolUrlLibrary("https://www.test.com")
//                .build();
//
//        SchoolInfoDTO result1 = schoolService.createSchool(schoolInfoDTO).getBody();
//
//        schoolService.deleteSchool(result1.getSchoolName());
//
//        SchoolInfoDTO result2 = schoolService.getSchool(result1.getSchoolName()).getBody();
//
//        assertNull(result2);
//    }
//
//    @Test
//    @DisplayName("학교 전체 조회")
//    @Transactional
//    void findAllSchool() {
//        SchoolInfoDTO schoolInfoDTO = SchoolInfoDTO.builder()
//                .schoolName("테스트 대학교")
//                .build();
//
//        for (int i = 0; i < 10; i++) {
//            schoolInfoDTO.setSchoolName("테스트 대학교" + i);
//            schoolService.createSchool(schoolInfoDTO);
//        }
//
//        ArrayList<SchoolListDTO> result = schoolService.findAllSchool().getBody();
//
//        assertNotNull(result);
//
//    }

}
