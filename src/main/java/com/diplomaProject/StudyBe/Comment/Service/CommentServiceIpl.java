package com.diplomaProject.StudyBe.Comment.Service;

import com.diplomaProject.StudyBe.Comment.Comment;
import com.diplomaProject.StudyBe.Comment.Repository.CommentRepository;
import com.diplomaProject.StudyBe.Comment.web.dto.CommentDTO;
import com.diplomaProject.StudyBe.User.Repository.UserRepository;
import com.diplomaProject.StudyBe.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceIpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addComment(CommentDTO comment, User me) {

        User receiver = userRepository.findByEmail(comment.getReceiverEmail()).orElse(null);
        if(receiver != null)
        {
            Comment newComment = new Comment(me, receiver, comment.getComment());
            commentRepository.save(newComment);
        }

    }

    @Override
    public List<CommentDTO> getCommentsBySenderAndReceiver(User me, String OtherUserEmail) {
        User otherUser = userRepository.findByEmail(OtherUserEmail).orElse(null);
        if(otherUser != null)
        {
            return commentRepository.findBySenderAndReceiver(me.getId(), otherUser.getId()).stream().map((c) -> {
                return new CommentDTO(c.getReceiver().getEmail(),c.getComment());
            }).toList();
        }
        return null;
    }
}
