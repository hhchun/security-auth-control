package com.cmzn.permission.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        return "test" + id;
    }
}
