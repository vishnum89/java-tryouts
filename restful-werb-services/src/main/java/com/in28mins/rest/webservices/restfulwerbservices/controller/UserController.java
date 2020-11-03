package com.in28mins.rest.webservices.restfulwerbservices.controller;

import com.in28mins.rest.webservices.restfulwerbservices.dao.UserDaoService;
import com.in28mins.rest.webservices.restfulwerbservices.dao.UserRepo;
import com.in28mins.rest.webservices.restfulwerbservices.exception.UserNotFoundException;
import com.in28mins.rest.webservices.restfulwerbservices.models.Post;
import com.in28mins.rest.webservices.restfulwerbservices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserDaoService userDao;

    @Autowired
    UserRepo userRepo;

    @GetMapping(path = "/user")
    public List<User> getUserList() {
        //return userDao.getAllUser();
        return userRepo.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public EntityModel<User> findUser(@PathVariable String id) {
        //User user = userDao.getUser(Integer.parseInt(id));
        Optional<User> userOpt = userRepo.findById(Integer.parseInt(id));
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("User is not found");
        }

        EntityModel<User> entityModel = EntityModel.of(userOpt.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUserList());
        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        //User savedUser = userDao.addUser(user);
        User savedUser = userRepo.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable String id) {
        try {
            userRepo.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new UserNotFoundException("User not found!!");
        }
    }
    @GetMapping(path = "/user/{id}/posts")
    public List<Post> getAllPostsByUserId(@PathVariable String id){

        Optional<User> userOptional =  userRepo.findById(Integer.parseInt(id));
        if(userOptional.isPresent()){
            return userOptional.get().getPostList();
        }else{
            throw new UserNotFoundException("Not Found!!");
        }
    }
}
