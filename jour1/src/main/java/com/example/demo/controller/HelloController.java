package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${greeting.message}")
    private String greetingMessage;

    @GetMapping("/")
    public String hello() {
        return greetingMessage;
    }

    @GetMapping("/hello")
    public String helloAgain() {
        return "This is added to test devTools!";
    }
}