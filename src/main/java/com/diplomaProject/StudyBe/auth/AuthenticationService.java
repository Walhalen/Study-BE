package com.diplomaProject.StudyBe.auth;


import com.diplomaProject.StudyBe.User.Repository.UserRepository;
import com.diplomaProject.StudyBe.User.Role;
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

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;

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

        User user = new User(request.getFirst_name(), request.getLast_name(),request.getEmail(),passwordEncoder.encode(request.getPassword()),Role.USER);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {


//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(),
//                        request.getPassword())
//        );
        try {
            User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));


            var jwtToken = jwtService.generateToken(user);


            return new AuthenticationResponse(jwtToken);
        }catch(Exception error)
        {
            throw new AuthenticationException("This email is already in use");
        }

    }

}
