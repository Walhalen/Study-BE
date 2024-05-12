package com.diplomaProject.StudyBe.Comment.web.dto;


import com.diplomaProject.StudyBe.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class CommentDTO {
    private String receiverEmail;
    private String comment;


    public CommentDTO(String receiverEmail, String comment) {
        this.receiverEmail = receiverEmail;
        this.comment = comment;

    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    @Override
    public String toString() {
        return "CommentDTO{" +
                "receiverEmail='" + receiverEmail + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
