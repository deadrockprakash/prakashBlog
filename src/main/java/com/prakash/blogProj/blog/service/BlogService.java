package com.prakash.blogProj.blog.service;

import com.prakash.blogProj.blog.entity.Blog;
import com.prakash.blogProj.blog.repository.BlogRepository;
import com.prakash.blogProj.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getBlogsByAuthor(String author){
       return  blogRepository.findByAuthor(author);
   }

    public Blog saveBlog(BlogDTO blogDTO) {
        Blog blog = mapBlogDTOToBlog(blogDTO);
        return blogRepository.save(blog);

    }

    private Blog mapBlogDTOToBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setAuthor(blogDTO.getAuthor());
        blog.setContent(blogDTO.getContent());
        blog.setTitle(blogDTO.getTitle());
        blog.setCreatedDate(blogDTO.getCreatedDate());

        return blog;
    }

    public Boolean deleteBlog(Long id) {
        blogRepository.deleteById(id);
        return true;
    }

    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
        Blog blog = blogRepository.findById(id).get();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setCreatedDate(new Date());
        blogRepository.save(blog);
        return blogDTO;
    }
}
