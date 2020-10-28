package com.in28mins.rest.webservices.restfulwerbservices;

import org.springframework.web.bind.annotation.GetMapping;
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
}
