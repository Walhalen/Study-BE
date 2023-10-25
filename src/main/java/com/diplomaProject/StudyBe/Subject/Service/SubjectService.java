package com.diplomaProject.StudyBe.Subject.Service;


import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.User.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SubjectService {
    public void addSub(SubjectDto subject);

    public Subject getByName(String name);

}
