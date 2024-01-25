package com.diplomaProject.StudyBe.Subject.Service;

import com.diplomaProject.StudyBe.Subject.Repository.SubjectRepository;
import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectServiceIml implements SubjectService{

    @Autowired
    SubjectRepository subjectRepository;



    @Override
    public void addSub(SubjectDto subject) {
        subjectRepository.save(new Subject(subject.getName(), subject.getColor()));
    }

    @Override
    public Subject getByName(String name) {
        return subjectRepository.findByName(name).orElse(null);
    }

    @Override
    public List<Subject> findAllSubject() {
        return subjectRepository.findAll();
    }
}
