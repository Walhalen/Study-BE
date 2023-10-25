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
    @GetMapping("/getByEmail")
    public User getByEmail(@RequestBody String email)
    {
        return userService.getByEmail(email);
    }


    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findByID/{id}")
    public User getByID(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/addSubject")
    public void addSubject(@RequestBody SubjectRequestDto subjectRequest)
    {
        System.out.print(subjectRequest.getSubjectName());
        User user = userService.getByEmail(subjectRequest.getUserEmail());
        Subject subject = subjectService.getByName(subjectRequest.getSubjectName());
        userService.addSubject(subject, user);
    }

}
