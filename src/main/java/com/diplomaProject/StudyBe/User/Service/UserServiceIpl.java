package com.diplomaProject.StudyBe.User.Service;

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


        User user  = new User(registrationDto.getFirst_name(), registrationDto.getLast_name(),
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
    public User findByEmail(String email) {
        List<User> userList = findAll();
        Optional<User> userWithThatEmail = userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();

        return userWithThatEmail.orElse(null);
    }



}
