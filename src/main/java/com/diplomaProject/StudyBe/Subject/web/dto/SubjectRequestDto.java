package com.diplomaProject.StudyBe.Subject.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class SubjectRequestDto {


    @NotEmpty
    @Email
    private String userEmail;

    @NotEmpty
    private String SubjectName;


    public SubjectRequestDto(String userEmail, String subjectName) {
        this.userEmail = userEmail;
        this.SubjectName = subjectName;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
