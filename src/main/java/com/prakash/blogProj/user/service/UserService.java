package com.prakash.blogProj.user.service;

import com.prakash.blogProj.user.entity.User;
import com.prakash.blogProj.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(String userName){
        return userRepository.findByUserName(userName);
    }
}
