package com.prakash.blogProj.controller;

import com.prakash.blogProj.blog.service.BlogService;
import com.prakash.blogProj.comment.service.CommentService;
import com.prakash.blogProj.dto.BlogDTO;
import com.prakash.blogProj.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @GetMapping("/getBlogs/{author}")
    public HttpEntity getBlogs(@PathVariable(value = "author") String author){
        return new ResponseEntity(blogService.getBlogsByAuthor(author), HttpStatus.OK);
    }

    @PostMapping("/saveBlog")
    public HttpEntity saveBlog(@RequestBody BlogDTO blogDTO){

        return new ResponseEntity(blogService.saveBlog(blogDTO),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBlog/{id}")
    public HttpEntity deleteBlog(@PathVariable(value = "id") Long id) {
        return new ResponseEntity(blogService.deleteBlog(id),HttpStatus.OK);
    }
    @PutMapping("/updateBlog/{id}")
    public HttpEntity updateBlog(@PathVariable(value = "id") Long id,
                               @RequestBody BlogDTO blogDTO){
        return new ResponseEntity(blogService.updateBlog(id,blogDTO),HttpStatus.OK);
    }

    @GetMapping("/version")
    public HttpEntity getVersion(){
        return new ResponseEntity("version 1.0",HttpStatus.OK);
    }

    @GetMapping("/comments/getComments/{author}")
    public HttpEntity getAuthorComments(@PathVariable (value = "author") String author){
        return new  ResponseEntity(commentService.getCommentByAuthor(author), HttpStatus.OK);
    }

    @PostMapping("/comments/saveComment")
    public HttpEntity saveComment(@RequestBody CommentDTO commentDTO){
        return new ResponseEntity(commentService.saveComment(commentDTO),HttpStatus.OK);
    }

    @DeleteMapping("/comments/deleteComment/{commentId}")
    public HttpEntity deleteComment(@PathVariable(value = "commentId") Long id){
        return new ResponseEntity("deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/comments/updateComment/{id}")
    public HttpEntity updateComment(@PathVariable(value = "id") Long id, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity(commentService.updateComment(id,commentDTO),HttpStatus.OK);
    }
}
