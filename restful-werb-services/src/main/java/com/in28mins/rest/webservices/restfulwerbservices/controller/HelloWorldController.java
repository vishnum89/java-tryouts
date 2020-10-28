package com.in28mins.rest.webservices.restfulwerbservices.controller;

import com.in28mins.rest.webservices.restfulwerbservices.models.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }

    @GetMapping(path = "/helloWorldBean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("HelloWorld");
    }

    @GetMapping(path = "/helloWorldBean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean("HelloWorld " + name);
    }
}
