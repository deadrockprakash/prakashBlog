package com.prakash.blogProj.comment.service;

import com.prakash.blogProj.comment.entity.Comment;
import com.prakash.blogProj.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentByAuthor(String author){
        return commentRepository.findByAuthor(author);
    }
}
