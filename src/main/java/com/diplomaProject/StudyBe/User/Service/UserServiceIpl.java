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


//    @Override
//    public User save(UserRegistrationDto registrationDto) {
//
//
//        User user  = new User(registrationDto.getUsername(),
//                registrationDto.getEmail(),registrationDto.getPassword(), ADMIN );
//
//
//        return userRepository.save(user);
//    }

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
        if(user.getTags() == null)
        {
            user.setTags(new LinkedList<>());
        }
        user.getTags().add(subject);
        System.out.println(user.getTags());
        userRepository.save(user);
    }

    @Override
    public List<User> findFilteredUsers(String searchInfo) {
        List<User> allUsers = findAll();
        List<User> filteredUsers = new LinkedList<>();
        System.out.println(searchInfo);
        System.out.println(allUsers.get(0).getUsername().startsWith("Wahlal123"));
        for (User user: allUsers) {
            if(user.getUsername().startsWith(searchInfo))
            {
                filteredUsers.add(user);
            }
        }
        System.out.println(filteredUsers.size());
        return filteredUsers;
    }


}
