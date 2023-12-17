package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

//    public User save(UserRegistrationDto registrationDto);

    public List<User> findAll();

    public User findById(Long id);

    public User getByEmail(String email);

   public void addSubject(Subject subject, User user);

    public List<User> findFilteredUsers(String searchInfo);

    public List<User> findUsersByTag(String tag);

    public List<User> findFilteredUsersPageable(String searchInfo, int page);

    public List<User> findAllPageable(int page);

    public int findPagesCount();
}
