package com.smartcareer.repository;

import com.smartcareer.entity.Learner;
import com.smartcareer.entity.SaveCourse;
import com.smartcareer.entity.UniversityProgramme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedCourseRepository extends JpaRepository<SaveCourse, Long> {


    boolean existsByLearnerAndProgramme(Learner learner, UniversityProgramme programme);

    long countByLearner(Learner learner);

    List<SaveCourse> findByLearner(Learner learner);

    void deleteByLearnerAndProgramme(Learner learner, UniversityProgramme programme);

}