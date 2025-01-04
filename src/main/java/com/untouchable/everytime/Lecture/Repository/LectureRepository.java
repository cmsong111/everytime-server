package com.untouchable.everytime.Lecture.Repository;

import com.untouchable.everytime.Lecture.Entity.LectureEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<LectureEntity, Long> {

    @Query("select l from LectureEntity l join fetch l.school s where s.name = :schoolName and l.year = :year and l.semester = :semester")
    List<LectureEntity> findBySchool_SchoolNameAndYearAndSemester(@Param("schoolName") String schoolName, @Param("year") int year, @Param("semester") int semester);


}
