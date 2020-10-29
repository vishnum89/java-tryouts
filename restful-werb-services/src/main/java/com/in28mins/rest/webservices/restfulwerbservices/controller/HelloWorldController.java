package com.in28mins.rest.webservices.restfulwerbservices.controller;

import com.in28mins.rest.webservices.restfulwerbservices.models.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    @GetMapping(path = "/helloWorldBean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HelloWorld");
    }

    @GetMapping(path = "/helloWorldBean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean("HelloWorld " + name);
    }

    @GetMapping(path = "/helloWorld-international")
    public String helloWorldInternational() {
        System.out.println(LocaleContextHolder.getLocale());
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
