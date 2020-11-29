package com.pereira.httpservicesb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    @GetMapping("/hello-world")
    @ResponseBody
    public String greetings() {
        return "Hello, User!";
    }

}
