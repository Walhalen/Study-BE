package com.diplomaProject.StudyBe.Subject.Cotroller;


import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.diplomaProject.StudyBe.Subject.Service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService service;
    @PostMapping("/add")
    public void addSubject(@RequestBody SubjectDto subject){
        service.addSub(subject);
    }


}
