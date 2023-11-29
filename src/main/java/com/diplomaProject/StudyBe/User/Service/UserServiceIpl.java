package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.Subject.Repository.SubjectRepository;
import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.Repository.UserRepository;

import com.diplomaProject.StudyBe.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceIpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

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

        for (User user: allUsers) {
            if(user.getUsername().startsWith(searchInfo))
            {
                filteredUsers.add(user);
            }
        }
        System.out.println(filteredUsers.size());
        return filteredUsers;
    }

    @Override
    public List<User> findUsersByTag(String tag) {
        List<User> allUsers = findAll();
        List<User> filteredUsers = new LinkedList<>();




        for (User user: allUsers) {
            for(Subject subject  : user.getTags().stream().toList())
            {
                System.out.println("Hello");
                if(subject.getName().equals(tag))
                {
                    filteredUsers.add(user);
                    break;
                }
            }

        }


        System.out.println(filteredUsers.size());
        return filteredUsers;
    }


}
