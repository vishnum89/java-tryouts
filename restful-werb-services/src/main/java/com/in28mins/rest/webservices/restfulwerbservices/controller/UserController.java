package com.in28mins.rest.webservices.restfulwerbservices.controller;

import com.in28mins.rest.webservices.restfulwerbservices.dao.UserDaoService;
import com.in28mins.rest.webservices.restfulwerbservices.exception.UserNotFoundException;
import com.in28mins.rest.webservices.restfulwerbservices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDaoService userDao;

    @GetMapping(path = "/user")
    public List<User> getUserList() {
        return userDao.getAllUser();
    }

    @GetMapping(path = "/user/{id}")
    public User findUser(@PathVariable String id) {
        User user = userDao.getUser(Integer.parseInt(id));
        if (user == null) {
            throw new UserNotFoundException("User is not found");
        }
        return user;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User savedUser = userDao.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable String id) {
        if (!userDao.deleteUser(Integer.parseInt(id))) {
            throw new UserNotFoundException("User not found!!");
        }
    }
}
