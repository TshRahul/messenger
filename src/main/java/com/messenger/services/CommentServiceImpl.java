package com.messenger.services;

import com.messenger.dao.CommentDao;
import com.messenger.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements  CommentService {

    @Autowired
    CommentDao commentDao;


    @Override
    @Transactional
    public List<Comment> getMessagesByMessageId(long messageId) {
 List<Comment> comments = commentDao.getcomments(messageId);
       return comments;
    }

    @Override
    @Transactional
    public Comment addComment(long messageId, Comment comment) {

        return commentDao.saveComments(comment, messageId);
    }

    @Override
    @Transactional
    public Comment editComment(Comment comment, long messageId) {
        return null;
    }

    @Override
    @Transactional
    public String deleteComment(long messageId, long commentID) {

      return  commentDao.deleteComment(messageId, commentID);
    }
}
