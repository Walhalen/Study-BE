package com.diplomaProject.StudyBe.User.Controller;


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
    public User findByID(@RequestBody Long id){
        return userService.findById(id);
    }


}
