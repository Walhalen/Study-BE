package com.diplomaProject.StudyBe.User.Repository;

import com.diplomaProject.StudyBe.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
