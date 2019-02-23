package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping(value = "/login")
    public String toIndex(){
        return "login";
    }

    @GetMapping(value = "/register")
    public String toRegister(){
        return "register";
    }
}
