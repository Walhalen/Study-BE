package com.diplomaProject.StudyBe.auth;

import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import io.micrometer.common.lang.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;

import java.util.LinkedList;
import java.util.List;

public class RegisterRequest {



    @NotEmpty
    @Size(min=2, max = 20, message = "user name should have at least 2 characters and max 20")
    private String username;


    @NotEmpty
    @Email
    private String email;


    @NotEmpty
    @Size(min = 6,  message = "user password should have at least 6 characters")
    private String password;


    @Size(max = 500, message = "user description should have max 500 characters")
    private String description;

    private double rating = 0.0;

    private List<SubjectDto> tags = new LinkedList<SubjectDto>();



    public RegisterRequest() {
    }

    public RegisterRequest(String username, String email, String password, List<SubjectDto> tags, String description) {
        this.username = username;
        this.tags = tags;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<SubjectDto> getTags() {
        return tags;
    }

    public void setTags(List<SubjectDto> tags) {
        this.tags = tags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tags=" + tags +
                '}';
    }
}
