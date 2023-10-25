package com.diplomaProject.StudyBe.Subject.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

public class SubjectDto {

    @NotEmpty
    private String name;

    public SubjectDto() {
    }

    public SubjectDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
