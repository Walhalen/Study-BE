package com.diplomaProject.StudyBe.Comment.Service;

import com.diplomaProject.StudyBe.Comment.Comment;
import com.diplomaProject.StudyBe.Comment.web.dto.CommentDTO;
import com.diplomaProject.StudyBe.User.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public void addComment(CommentDTO comment, User me);

    public List<CommentDTO> getCommentsBySenderAndReceiver(User me, String OtherUserEmail);
}
