package com.diplomaProject.StudyBe.Subject.Repository;

import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
}
