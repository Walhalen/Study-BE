package com.diplomaProject.StudyBe.Comment.Repository;

import com.diplomaProject.StudyBe.Comment.Comment;
import com.diplomaProject.StudyBe.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE (c.Sender.id = :senderId AND c.Receiver.id = :receiverId) OR (c.Sender.id = :receiverId AND c.Receiver.id = :senderId)")
    List<Comment> findBySenderAndReceiver(Long senderId, Long receiverId);


}
