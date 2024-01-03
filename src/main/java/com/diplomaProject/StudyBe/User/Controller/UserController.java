package com.diplomaProject.StudyBe.User.Controller;


import com.diplomaProject.StudyBe.Subject.Service.SubjectService;
import com.diplomaProject.StudyBe.Subject.Subject;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectDto;
import com.diplomaProject.StudyBe.Subject.web.dto.SubjectRequestDto;
import com.diplomaProject.StudyBe.User.Service.UserService;
import com.diplomaProject.StudyBe.User.User;
import com.diplomaProject.StudyBe.User.web.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @GetMapping("/getMe")
    public UserDto getMe(HttpServletRequest request){
        User me = (User) request.getAttribute("me");
        return new UserDto(me.getUsername(), me.getEmail(), me.getTags(),me.getFavorites(), me.getDescription(), me.getRating());
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        try {
            return userService.findAll();
        }catch(Exception error)
        {
            throw error;
        }
    }

    @GetMapping("/findAllPageable")
    public List<User> findAllPageable(@RequestParam int page )
    {
        return userService.findAllPageable(page);
    }

    @GetMapping("/findFilteredUsers/{searchInfo}")
    public List<User> findFiltered(@PathVariable String searchInfo){
        return userService.findFilteredUsers(searchInfo);
    }

    @GetMapping("/findFilteredUsersPageable")
    public List<User> findFilteredUsersPageable(@RequestParam String searchInfo, @RequestParam int page)
    {
        System.out.println("Searched info " + searchInfo);
        if(searchInfo.equals(""))
            return userService.findAllPageable(page);
        else
            return userService.findFilteredUsersPageable(searchInfo, page);
    }


    @GetMapping("/findUsersByTag/{tag}")
    public List<User> findFilteredUsers(@PathVariable String tag)
    {
        return userService.findUsersByTag(tag);
    }
    @GetMapping("/findByID/{id}")
    public User getByID(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/findPagesCount")
    public int findPagesCount(@RequestParam String searchValue, @RequestParam String subjectId)
    {

        if(subjectId != "")
        {
            return userService.findPagesCountBySubject(subjectId);
        }
        if(searchValue.equals(""))
            return userService.findPagesCount();
        else
            return userService.findPageCountFilter(searchValue);
    }

    @PostMapping("/addSubject")
    public void addSubject(@RequestBody SubjectRequestDto subjectRequest)
    {
        System.out.print(subjectRequest.getSubjectName());
        User user = userService.getByEmail(subjectRequest.getUserEmail());
        Subject subject = subjectService.getByName(subjectRequest.getSubjectName());
        userService.addSubject(subject, user);
    }

    @PostMapping("/addFavorite")
    public void addFavorite(@RequestParam String favoriteEmail, HttpServletRequest request)
    {
        User me = (User) request.getAttribute("me");
        System.out.println("alo da");
        userService.addFavorite(favoriteEmail, me);
    }


    @PostMapping("/removeFavorite")
    public void removeFavorite(@RequestParam String favoriteEmail,  HttpServletRequest request)
    {
        User me = (User) request.getAttribute("me");
        userService.removeFavorite(favoriteEmail, me);
    }

}
