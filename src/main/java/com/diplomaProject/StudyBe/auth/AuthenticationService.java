package com.diplomaProject.StudyBe.auth;


import com.diplomaProject.StudyBe.User.Repository.UserRepository;
import com.diplomaProject.StudyBe.User.Role;
import com.diplomaProject.StudyBe.configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.auth.AuthenticationResponse;
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


    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        System.out.println("Helloo 2 ");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        System.out.println("Helloo 3");
        User user  = repository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        System.out.println(jwtToken);
        return new AuthenticationResponse(jwtToken);
    }

}
