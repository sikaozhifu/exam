package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/register")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/forgot")
    public String toForgot() {
        return "forgot";
    }

    @RequestMapping(value = "/adminLogin")
    public String toAdminLogin() {
        return "adminLogin";
    }

    @RequestMapping(value = "/adminForgot")
    public String toAdminForgot() {
        return "adminForgot";
    }

    @RequestMapping(value = "/adminTable")
    public String toAdminTable() {
        return "adminTable";
    }

    @RequestMapping(value = "/adminIndex")
    public String toAdminIndex() {
        return "adminIndex";
    }

    @RequestMapping(value = "/studentIndex")
    public String toStudentIndex() {
        return "student";
    }

    @RequestMapping(value = "/teacherIndex")
    public String toTeacherIndex() {
        return "teacher_index";
    }
}
