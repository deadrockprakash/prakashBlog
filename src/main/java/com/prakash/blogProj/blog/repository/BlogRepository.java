package com.prakash.blogProj.blog.repository;

import com.prakash.blogProj.blog.entity.Blog;
import com.prakash.blogProj.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Comment,Long> {
    List<Blog> findByAuthor(String author);
}
