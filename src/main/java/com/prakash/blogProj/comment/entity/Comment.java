package com.prakash.blogProj.comment.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id")
    private Long blog_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "author")
    private String author;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;


    public Comment(){

    }

    public Comment(Long blog_id, String comment, String author, Date createDate, Date modifiedDate) {
        this.blog_id = blog_id;
        this.comment = comment;
        this.author = author;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Long blog_id) {
        this.blog_id = blog_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
