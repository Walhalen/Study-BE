package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.User.Repository.UserRepository;
import com.diplomaProject.StudyBe.Role.Role;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceIpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user  = new User(registrationDto.getFirst_name(), registrationDto.getLast_name(),
                registrationDto.getEmail(),registrationDto.getPassword(), Arrays.asList(new Role("Admin")));


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
