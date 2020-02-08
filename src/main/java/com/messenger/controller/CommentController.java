package com.messenger.controller;

import com.messenger.model.Comment;
import com.messenger.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/message")
public class CommentController {

    @Autowired
    private
    CommentService commentService;

    @GetMapping("{messageId}/comment")
    public List<Comment> getCommentsOfMessage(@PathVariable long messageId){
        return commentService.getMessagesByMessageId(messageId);
    }

    @PostMapping("{messageId}/comment")
    public Comment commentOnMessage(@PathVariable long messageId, @RequestBody Comment comment){
        return commentService.addComment(messageId, comment);
    }

  @DeleteMapping("{messageId}/comment/{commentId}")
    public String removeComment(@PathVariable long messageId, @PathVariable long commentId){
        return commentService.deleteComment(messageId, commentId);
  }


}
