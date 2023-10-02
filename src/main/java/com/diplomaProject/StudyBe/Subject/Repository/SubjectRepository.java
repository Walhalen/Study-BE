package com.diplomaProject.StudyBe.Subject.Repository;

import com.diplomaProject.StudyBe.Subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
