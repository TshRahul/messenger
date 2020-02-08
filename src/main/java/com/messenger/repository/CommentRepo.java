package com.messenger.repository;

import com.messenger.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

     List<Comment> findByMessageId(long message_id);

     Comment findById(long comment_id);
}
