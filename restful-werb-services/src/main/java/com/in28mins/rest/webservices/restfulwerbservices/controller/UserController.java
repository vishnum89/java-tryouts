package com.in28mins.rest.webservices.restfulwerbservices.controller;

import com.in28mins.rest.webservices.restfulwerbservices.dao.UserDao;
import com.in28mins.rest.webservices.restfulwerbservices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping(path = "/user")
    public List<User> getUserList(){
        return userDao.getAllUser();
    }

    @GetMapping(path = "/user/{id}")
    public User findUser(@PathVariable String id){
        return userDao.getUser(Integer.parseInt(id));
    }

    @PostMapping(path = "user")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User savedUser = userDao.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
