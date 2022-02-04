package com.prakash.blogProj.blog.service;

import com.prakash.blogProj.blog.entity.Blog;
import com.prakash.blogProj.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getBlogsByAuthor(String author){
       return  blogRepository.findByAuthor(author);
   }
}
