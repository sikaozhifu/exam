package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/register")
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "/adminLogin")
    public String toAdminLogin(){
        return "adminLogin";
    }
    @RequestMapping(value = "/adminTable")
    public String toAdminTable(){
        return "adminTable";
    }
    @RequestMapping(value = "/adminIndex")
    public String toAdminIndex(){
        return "adminIndex";
    }
}
