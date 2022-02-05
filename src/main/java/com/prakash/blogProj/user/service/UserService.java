package com.prakash.blogProj.user.service;

import com.prakash.blogProj.dto.UserDTO;
import com.prakash.blogProj.user.entity.User;
import com.prakash.blogProj.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(String userName){
        return userRepository.findByUserName(userName);
    }

    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public boolean deleteUser(Long id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
        return  true;
    }

    public UserDTO updateUser(String userName, UserDTO userDTO) {
        User user = userRepository.findByUserName(userName);
        user.setLastName(userDTO.getUserName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return userDTO;
    }
}
