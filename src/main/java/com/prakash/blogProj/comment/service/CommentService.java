package com.prakash.blogProj.comment.service;

import com.prakash.blogProj.comment.entity.Comment;
import com.prakash.blogProj.comment.repository.CommentRepository;
import com.prakash.blogProj.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentByAuthor(String author){
        return commentRepository.findByAuthor(author);
    }

    public Comment saveComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setBlogId(commentDTO.getBlogId());
        comment.setCreateDate(new Date());
        comment.setModifiedDate(new Date());
        commentRepository.save(comment);
        return comment;
    }

    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).get();
        comment.setComment(commentDTO.getComment());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setModifiedDate(new Date());
        commentRepository.save(comment);
        return commentDTO;
    }
}
