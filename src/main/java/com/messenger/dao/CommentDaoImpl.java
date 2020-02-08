package com.messenger.dao;

import com.messenger.exceptions.BadRequestException;
import com.messenger.exceptions.RecordNotFoundException;
import com.messenger.model.Comment;
import com.messenger.model.Message;
import com.messenger.repository.CommentRepo;
import com.messenger.repository.MessageRepo;
import com.messenger.utilities.BasicUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class CommentDaoImpl implements  CommentDao {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    MessageRepo messageRepo;

    BasicUtility baseUtil = new BasicUtility();


    @Override
    public List<Comment> getcomments(long messageId) {
         Session currentSession = entityManager.unwrap(Session.class);

        Message message = messageRepo.findById(messageId);

        if(message == null) {
            throw new RecordNotFoundException("The message with message id : " + messageId + " is not present");
        }

       return commentRepo.findByMessageId(messageId);
    }

    @Override
    public Comment saveComments(Comment comment, long messageId) {
        Message message = messageRepo.findById(messageId);

        if(message == null) {
            throw new RecordNotFoundException("The message with message id : " + messageId + " is not present");
        }
        if(comment.getComment().equals(null)) {
            throw new BadRequestException("Empty message is not accepted");
        }

        Session currentSession = entityManager.unwrap(Session.class);
        try {
            Query query = currentSession.createSQLQuery(baseUtil.getValuesFromPropertyFile("isUserAlreadyExits").replace("%username%",comment.getAuthor()));

            List isUserExists = query.getResultList();
            if(isUserExists.get(0).toString().equals("false")) {
                throw new RecordNotFoundException("The user with user name : " + comment.getAuthor() + " is not present") ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Date date= Calendar.getInstance().getTime();

        comment.setCreated_date(date);
        comment.setIs_deleted(false);
        comment.setModification_date(null);
        comment.setMessage(message);
        currentSession.saveOrUpdate(comment);
        currentSession.close();
        System.out.println(comment);
        return comment;
    }

    @Override
    public String deleteComment(long messageId, long commentId) {

        Message message = messageRepo.findById(messageId);

        if(message == null) {
            throw new RecordNotFoundException("The message with message id : " + messageId + " is not present");
        }

        Comment comment = commentRepo.findById(commentId);
        if(comment == null) {
            throw new RecordNotFoundException("The comment with comment id : " + commentId + " is not present");
        }
        Session currentSession = entityManager.unwrap(Session.class);

        comment.setIs_deleted(true);
        currentSession.saveOrUpdate(comment);
     //   Comment commentAfterDeletion = commentRepo.findByCommentID(commentId);
     return "  Comment with comment id : " + commentId + " is deleted successfully";
    }


}
