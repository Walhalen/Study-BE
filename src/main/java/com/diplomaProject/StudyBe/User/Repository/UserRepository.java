package com.diplomaProject.StudyBe.User.Repository;

import com.diplomaProject.StudyBe.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT(?1, '%'))")
    Page<User> findFilteredUsers(Pageable pageable, String searchInfo);


    @Query("SELECT COUNT(*) FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT(?1, '%'))")
    int findPageCountFilter(String searchValue);

    @Query("SELECT COUNT(u) FROM User u JOIN u.tags t WHERE t.name = ?1")
    int findPageCountBySubject(String subjectId);

    @Query("SELECT DISTINCT u.favorites FROM User u LEFT JOIN  u.favorites f WHERE u.id = ?1")
    List<Object> test(int userId);



}
 