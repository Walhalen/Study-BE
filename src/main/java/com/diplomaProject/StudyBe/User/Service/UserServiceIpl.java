package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.Repository.UserRepository;

import com.diplomaProject.StudyBe.User.Role;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
import org.hibernate.metamodel.internal.AbstractDynamicMapInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.diplomaProject.StudyBe.User.Role.ADMIN;

@Service
public class UserServiceIpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(UserRegistrationDto registrationDto) {


        User user  = new User(registrationDto.getUsername(),
                registrationDto.getEmail(),registrationDto.getPassword(), ADMIN );


        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {

         return userRepository.findAll();
    }

    @Override
    public User findById(Long id ) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void addSubject(Subject subject, User user) {
        user.getSubjects().add(subject);
        userRepository.save(user);
    }


}
