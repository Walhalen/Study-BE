package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public User save(UserRegistrationDto registrationDto);

    public List<User> findAll();

    public User findById(Long id);

    public User findByEmail(String email);


}
