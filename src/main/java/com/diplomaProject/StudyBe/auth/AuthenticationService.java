package com.diplomaProject.StudyBe.auth;


import com.diplomaProject.StudyBe.Subject.Service.SubjectService;
import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.Repository.UserRepository;
import com.diplomaProject.StudyBe.User.Role;
import com.diplomaProject.StudyBe.User.Service.UserService;
import com.diplomaProject.StudyBe.User.web.dto.FavoriteOrHistoryUserDto;
import com.diplomaProject.StudyBe.User.web.dto.MeUserDto;
import com.diplomaProject.StudyBe.User.web.dto.UserDto;
import com.diplomaProject.StudyBe.configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.auth.AuthenticationResponse;

import javax.naming.AuthenticationException;
import java.util.Collection;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;


    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request)
    {
//        var user = User.builder()
//                .firstname(request.getFirst_name())
//                .lastname(request.getLast_name())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();

        User user = new User(request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()), request.getDescription(), request.getRating(),Role.USER);
        repository.save(user);
        for(SubjectDto subject : request.getTags())
        {
            System.out.println(subject.toString());
            Subject newSubject = subjectService.getByName(subject.getName());
            userService.addSubject(newSubject, user);
        }
        Collection<FavoriteOrHistoryUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
            return new FavoriteOrHistoryUserDto(favorite.getUsername(),favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
        }).toList();
        Collection<FavoriteOrHistoryUserDto> history = user.getHistory().stream().map((historyUser) -> {
            return new FavoriteOrHistoryUserDto(historyUser.getUsername(),historyUser.getEmail(), historyUser.getTags(), historyUser.getDescription(), historyUser.getRating());
        }).toList();
        MeUserDto me = new MeUserDto(user.getUsername(), user.getEmail(), user.getTags(), favorites,history, user.getDescription(), user.getRating());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken, me);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
        System.out.println(request.toString());

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(),
//                        request.getPassword())
//        );
        try {
            User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));


            var jwtToken = jwtService.generateToken(user);
            Collection<FavoriteOrHistoryUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
                return new FavoriteOrHistoryUserDto(favorite.getUsername(), favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
            }).toList();
            Collection<FavoriteOrHistoryUserDto> history = user.getHistory().stream().map((historyUser) -> {
                return new FavoriteOrHistoryUserDto(historyUser.getUsername(),historyUser.getEmail(), historyUser.getTags(), historyUser.getDescription(), historyUser.getRating());
            }).toList();
            MeUserDto me = new MeUserDto(user.getUsername(), user.getEmail(), user.getTags(), favorites,history, user.getDescription(), user.getRating());
            System.out.println("the me : "  + me.getUsername());
            return new AuthenticationResponse(jwtToken, me);
        }catch(Exception error)
        {
            throw new AuthenticationException("This email is already in use");
        }

    }

}
