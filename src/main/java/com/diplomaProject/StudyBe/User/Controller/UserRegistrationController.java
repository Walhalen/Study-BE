    package com.diplomaProject.StudyBe.User.Controller;

    import com.diplomaProject.StudyBe.User.Service.UserService;
    import com.diplomaProject.StudyBe.User.web.dto.UserRegistrationDto;
    import com.diplomaProject.StudyBe.User.Service.UserServiceIpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/user/registration")

    public class UserRegistrationController {

        @Autowired
        private UserService userService;

        @GetMapping("/hello")
        public String hello() {
            System.out.println("Hello");
            return "Hello";
        }

        @GetMapping("/error")
        public String handleError() {
            // Return the name of your custom error page for 404 errors
            return "error_404";
        }

        @PostMapping("/save")
        public String saveNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
            this.userService.save(userRegistrationDto);
            return userRegistrationDto.getFirst_name();
        }

        @GetMapping("") // Map to /registration/
        public String registration() {
            // Add any logic for /registration/ here
            return "Registration Page";
        }



    }
