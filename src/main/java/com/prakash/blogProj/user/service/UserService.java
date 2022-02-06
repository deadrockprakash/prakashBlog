package com.prakash.blogProj.user.service;

import com.prakash.blogProj.dto.RoleDTO;
import com.prakash.blogProj.dto.UserDTO;
import com.prakash.blogProj.user.entity.Role;
import com.prakash.blogProj.user.entity.User;
import com.prakash.blogProj.user.repository.RoleRepository;
import com.prakash.blogProj.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserService  implements  UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username {} :",username);
        User user = userRepository.findByUserName(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found in the data");
        } else {
            log.info("User found in the database: {}" ,username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }

    public Role saveRole(RoleDTO roleDto){
        Role role = new Role();
        role.setName(roleDto.getName());
        roleRepository.save(role);
        return role;
    }

    public User getUser(String userName){
        return userRepository.findByUserName(userName);
    }

    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
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

    public boolean userAlreadyExist(String userName) {
       User user =  getUser(userName);
       if (user!=null)
           return true;
       return false;
    }

    public boolean roleExist(String rolename) {
        Role role = roleRepository.findByName(rolename);
        if(role!=null)
            return true;
        return false;
    }
}
