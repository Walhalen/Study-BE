package com.diplomaProject.StudyBe.auth;


import com.diplomaProject.StudyBe.User.web.dto.UserDto;
import jdk.jfr.DataAmount;

public class AuthenticationResponse  {

    private String token;

    private UserDto user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, UserDto user) {
        this.token = token;
        System.out.println(user);
        this.user = user;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
