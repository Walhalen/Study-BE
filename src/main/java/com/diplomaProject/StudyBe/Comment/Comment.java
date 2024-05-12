package com.diplomaProject.StudyBe.Comment;


import com.diplomaProject.StudyBe.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender",  referencedColumnName = "id", nullable=false)
    private User Sender;

    @ManyToOne
    @JoinColumn(name = "receiver",  referencedColumnName = "id", nullable = false)
    private User Receiver;
    @Column(name = "comment")
    private String comment;
    @Column(name = "time")
    private  LocalDateTime currentTimeStamp;

    public Comment() {
    }

    public Comment(User sender, User receiver, String comment) {
        Sender = sender;
        Receiver = receiver;
        this.comment = comment;
        this.currentTimeStamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return Sender;
    }

    public User getReceiver() {
        return Receiver;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public void setReceiver(User receiver) {
        Receiver = receiver;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Sender=" + Sender +
                ", Receiver=" + Receiver +
                ", comment='" + comment + '\'' +
                ", currentTimeStamp=" + currentTimeStamp +
                '}';
    }
}
