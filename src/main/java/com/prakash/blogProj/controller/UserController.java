package com.prakash.blogProj.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prakash.blogProj.dto.RoleDTO;
import com.prakash.blogProj.dto.UserDTO;
import com.prakash.blogProj.user.entity.Role;
import com.prakash.blogProj.user.entity.User;
import com.prakash.blogProj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    public HttpEntity saveUser(@RequestBody UserDTO userDTO) throws Exception {
        if(userService.userAlreadyExist(userDTO.getUserName())) {
            throw new Exception("User Already Exist");
        }
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

    @PostMapping("/role/saveRole")
    public HttpEntity saveRole(@RequestBody RoleDTO roleDTO) throws Exception {
        String rolename = roleDTO.getName();
        if(userService.roleExist(rolename)){
            throw new Exception("Role already exits");
        }
        return new ResponseEntity(userService.saveRole(roleDTO),HttpStatus.OK);
    }


    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("role",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }catch (Exception exception){
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

//    @PostMapping("/role/addRoleToUser")
//    public HttpEntity addRoleToUser(@RequestBody RoleToUSerFormDTO roleToUSerFormDTO)
//    {
//        userService.addRoleToUser(roleToUSerFormDTO.getUserName(),roleToUSerFormDTO.getRoleName());
//        return new ResponseEntity("role added successfully",HttpStatus.OK);
//    }

}
