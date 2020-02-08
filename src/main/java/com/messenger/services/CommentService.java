package com.messenger.services;

import com.messenger.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getMessagesByMessageId(long messageId);

    Comment addComment(long messageId, Comment comment);

    Comment editComment(Comment comment, long messageId);

    String deleteComment(long messageId, long commentID);
}
