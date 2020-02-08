package com.bleeding.ironbox.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testCtrl {
    @RequestMapping("/test")
    public String test(){
        return "Hello! My IronBox!";
    }
}
