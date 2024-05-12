package com.diplomaProject.StudyBe.Comment.Controller;


import com.diplomaProject.StudyBe.Comment.Comment;
import com.diplomaProject.StudyBe.Comment.Service.CommentService;
import com.diplomaProject.StudyBe.Comment.web.dto.CommentDTO;
import com.diplomaProject.StudyBe.User.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/addComment")
    public void addComment(@RequestBody CommentDTO comment, HttpServletRequest request)
    {
        User me = (User) request.getAttribute("me");
        service.addComment(comment, me);
    }


    @GetMapping("/getCommentsBySenderAndReceiver")
    public List<CommentDTO> getCommentsBySenderAndReceiver(@RequestParam String OtherUserEmail, HttpServletRequest request)
    {
        User me = (User) request.getAttribute("me");
        return service.getCommentsBySenderAndReceiver(me, OtherUserEmail);
    }
}
