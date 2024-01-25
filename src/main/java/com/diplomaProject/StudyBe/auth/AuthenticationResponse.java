package com.diplomaProject.StudyBe.auth;


import com.diplomaProject.StudyBe.User.web.dto.MeUserDto;
import com.diplomaProject.StudyBe.User.web.dto.UserDto;
import jdk.jfr.DataAmount;

public class AuthenticationResponse  {

    private String token;

    private MeUserDto user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, MeUserDto user) {
        this.token = token;
        System.out.println(user);
        this.user = user;
    }

    public MeUserDto getUser() {
        return user;
    }

    public void setUser(MeUserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
