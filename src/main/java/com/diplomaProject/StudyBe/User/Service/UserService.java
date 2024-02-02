package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserDto;
import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

//    public User save(UserRegistrationDto registrationDto);

    public List<UserDto> findAll();

    public User findById(Long id);

    public User getByEmail(String email);

   public void addSubject(Subject subject, User user);

    public List<UserDto> findFilteredUsers(String searchInfo);

    public List<UserDto> findUsersByTag(String tag);

    public List<UserDto> findFilteredUsersPageable(String searchInfo, int page);

    public List<UserDto> findAllPageable(int page);

    public int findPagesCount();

    public int findPageCountFilter(String searchValue);

    public int findPagesCountBySubject(String subjectId);

    public void addFavorite(String favoriteEmail, User me);
    public void removeFavorite (String favoriteEmail, User me);

    public void addHistory(String historyEmail, User me);

    public void removeHistory(String historyEmail, User me);

    public List<Object> test(int id);
}
