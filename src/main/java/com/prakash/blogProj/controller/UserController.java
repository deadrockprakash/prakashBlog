package com.prakash.blogProj.controller;

import com.prakash.blogProj.comment.service.CommentService;
import com.prakash.blogProj.dto.CommentDTO;
import com.prakash.blogProj.dto.UserDTO;
import com.prakash.blogProj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/user/getAllUser")
    public HttpEntity getAllUser(){
        return new ResponseEntity(userService.getAllUser(),HttpStatus.OK);
    }

    @GetMapping("/user/getUser/{userName}")
    public HttpEntity getUser(@PathVariable(value = "id") String userName){
        return new ResponseEntity(userService.getUser(userName),HttpStatus.OK);
    }

    @PostMapping("/user/addUser")
    public HttpEntity saveUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity(userService.saveUser(userDTO),HttpStatus.OK);
    }

    @DeleteMapping("/user/deleteUser/{id}")
    public HttpEntity deleteUser(@PathVariable(value = "id") Long id){
        return new ResponseEntity(userService.deleteUser(id),HttpStatus.OK);
    }

    @PutMapping("/user/updateUser/{userName}")
    public HttpEntity updateUser(@PathVariable(value = "userName") String userName, @RequestBody UserDTO userDTO){
        return new ResponseEntity(userService.updateUser(userName,userDTO),HttpStatus.OK);
    }





}
