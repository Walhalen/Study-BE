package com.diplomaProject.StudyBe.Subject.web.dto;

public class SubjectRequestDto {

    private String userEmail;
    private String SubjectName;

    public SubjectRequestDto(String userEmail, String subjectName) {
        this.userEmail = userEmail;
        SubjectName = subjectName;
    }

    public SubjectRequestDto() {
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
