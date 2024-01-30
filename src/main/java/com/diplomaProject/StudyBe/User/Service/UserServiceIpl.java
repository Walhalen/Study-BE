package com.diplomaProject.StudyBe.User.Service;

import com.diplomaProject.StudyBe.Subject.Repository.SubjectRepository;
import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.Repository.UserRepository;

import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.FavoriteUserDto;
import com.diplomaProject.StudyBe.User.web.dto.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceIpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<UserDto> findAll() {

        List<UserDto> users = userRepository.findAll().stream().map((user) -> {
             Collection<FavoriteUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
                 return new FavoriteUserDto(favorite.getUsername(),favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
             }).toList();
             return new UserDto(user.getUsername(),user.getEmail(), user.getTags(), user.getDescription(), favorites, user.getRating());
         }).toList();
         return users;
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
    public List<UserDto> findFilteredUsers(String searchInfo) {
        List<UserDto> allUsers = findAll();
        List<UserDto> filteredUsers = new LinkedList<>();

        for (UserDto user: allUsers) {
            if(user.getUsername().startsWith(searchInfo))
            {
                filteredUsers.add(user);
            }
        }
        System.out.println(filteredUsers.size());
        return filteredUsers;
    }

    @Override
    public List<UserDto> findUsersByTag(String tag) {
        List<UserDto> allUsers = findAll();
        List<UserDto> filteredUsers = new LinkedList<>();




        for (UserDto user: allUsers) {
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

    @Override
    public List<UserDto> findFilteredUsersPageable(String searchInfo, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return userRepository.findFilteredUsers(pageable, searchInfo).getContent().stream().map((user) -> {
            Collection<FavoriteUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
                return new FavoriteUserDto(favorite.getUsername(),favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
            }).toList();
            return new UserDto(user.getUsername(),user.getEmail(), user.getTags(), user.getDescription(), favorites, user.getRating());
        }).toList();
    }

    @Override
    public List<UserDto> findAllPageable(int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return userRepository.findAll(pageable).getContent().stream().map((user) -> {
            Collection<FavoriteUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
                return new FavoriteUserDto(favorite.getUsername(),favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
            }).toList();
            return new UserDto(user.getUsername(),user.getEmail(), user.getTags(), user.getDescription(), favorites, user.getRating());
        }).toList();

    }

    @Override
    public int findPagesCount(){
        int count = (int)userRepository.count();
        if(count % 12 == 0)
            return count / 12;
        else
            return count / 12  + 1;
    }

    @Override
    public int findPageCountFilter(String searchValue){
        int count = userRepository.findPageCountFilter(searchValue);

        if(count % 12 == 0)
            return count / 12;
        else
            return count / 12  + 1;
    }

    @Override
    public int findPagesCountBySubject(String subjectId) {
        int count = userRepository.findPageCountBySubject(subjectId);
        System.out.println("Pages Count " + count);
        if(count % 12 == 0)
            return count / 12;
        else
            return count / 12  + 1;
    }

    @Override
    @Transactional
    public void addFavorite(String favoriteEmail, User me) {
        User favoritePerson = userRepository.findByEmail(favoriteEmail).orElse(null);


        if (favoritePerson != null && me != null) {
            System.out.println("alo da 2");

            if (me.getFavorites() == null) {
                System.out.println("Alo da 3");
                me.setFavorites(new LinkedList<>());
            }
            if (!entityManager.contains(me)) {
                me = entityManager.merge(me);
            }

            if (!me.getFavorites().contains(favoritePerson)) {
                System.out.println("alo da 4");
                System.out.println(me.getFavorites());
                System.out.println(favoritePerson.getId());
                me.getFavorites().add(favoritePerson);
                System.out.println(me.getFavorites());

                userRepository.saveAndFlush(me);

            }
        }
    }

    @Override
    @Transactional
    public void removeFavorite(String favoriteEmail, User me) {
        User favoritePerson = userRepository.findByEmail(favoriteEmail).orElse(null);
        if (favoritePerson != null && me != null)
        {
            if (!entityManager.contains(me)) {
                me = entityManager.merge(me);
            }
            System.out.println("in");
            me.getFavorites().remove(favoritePerson);
            userRepository.saveAndFlush(me);
        }

    }

    @Override
    public List<Object> test(int id){
        return userRepository.test(id);
    }


}
