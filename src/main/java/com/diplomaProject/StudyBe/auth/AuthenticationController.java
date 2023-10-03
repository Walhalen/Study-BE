package com.diplomaProject.StudyBe.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    };

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)throws AuthenticationException
    {
        System.out.println("HELLOOOOOOOOO");
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        try{
            return ResponseEntity.ok(service.authenticate(request));
        }catch(AuthenticationException e)
        {
            throw e;
        }

    };
}
