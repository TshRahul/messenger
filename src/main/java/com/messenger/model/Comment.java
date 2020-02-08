package com.messenger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="COMMENTS")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(name="message_id")
//    @NotEmpty(message = "message id must not be empty")
//    private long messageId;

    @Column(name="comment")
    @NotEmpty(message = "comment must not be empty")
    private String comment;

    @Column(name="comment_author")
    @NotEmpty(message = "comment author must not be empty")
    private String author;

    @Column(name="ceated_date")
    private Date created_date;

    @Column(name="modified_date")
    private Date modification_date;

    @Column(name="is_comment_deleted")
    private boolean is_deleted;

    @ManyToOne
    private Message message;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public long getMessage_id() {
//        return messageId;
//    }
//
//    public void setMessage_id(long messageId) {
//        this.messageId = messageId;
//    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getModification_date() {
        return modification_date;
    }

    public void setModification_date(Date modification_date) {
        this.modification_date = modification_date;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @JsonIgnore
    @JsonProperty(value = "message")
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
//                ", message_id=" + messageId +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", created_date=" + created_date +
                ", modification_date=" + modification_date +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
