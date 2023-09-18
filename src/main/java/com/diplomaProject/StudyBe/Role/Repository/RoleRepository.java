package com.diplomaProject.StudyBe.Role.Repository;

import com.diplomaProject.StudyBe.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
