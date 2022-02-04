package com.prakash.blogProj.controller;

import com.prakash.blogProj.blog.entity.Blog;
import com.prakash.blogProj.blog.service.BlogService;
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

    @GetMapping("/getBlogs/{author}")
    public HttpEntity getBlogs(@PathVariable(value = "author") String author){
        return new ResponseEntity(blogService.getBlogsByAuthor(author), HttpStatus.OK);
    }

    @PostMapping("/saveBlog")
    public HttpEntity saveBlog(@RequestBody Blog blog){
        return new ResponseEntity("blog saved",HttpStatus.OK);
    }
}
