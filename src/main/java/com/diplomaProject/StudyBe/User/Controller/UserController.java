package com.diplomaProject.StudyBe.User.Controller;


import com.diplomaProject.StudyBe.Subject.Service.SubjectService;
import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectRequestDto;
import com.diplomaProject.StudyBe.User.Service.UserService;
import com.diplomaProject.StudyBe.User.User;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;
    @GetMapping("/findByEmail")
    public User findByEmail(@RequestBody String email)
    {
        return userService.findByEmail(email);
    }


    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findByID")
    public User findByID(){
        return userService.findById(1L);
    }

    @PutMapping("/addSubject")
    public void addSubject(@RequestBody SubjectRequestDto subjectRequest)
    {
        System.out.println(subjectRequest);
        User user = userService.findByEmail(subjectRequest.getUserEmail());
        Subject subject = subjectService.findByName(subjectRequest.getSubjectName());
        userService.addSubject(subject, user);
    }

}
