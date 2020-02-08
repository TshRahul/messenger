package com.messenger.dao;

import com.messenger.model.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> getcomments(long messageId);

    Comment saveComments(Comment comment, long messageId);

    String deleteComment(long messageId, long commentId);

}
