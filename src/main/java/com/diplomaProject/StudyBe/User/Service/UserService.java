package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User save(UserRegistrationDto registrationDto);
}
