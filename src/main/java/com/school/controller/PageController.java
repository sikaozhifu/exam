package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    //first

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

    //admin

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

    //student

    @RequestMapping(value = "/studentIndex")
    public String toStudentIndex() {
        return "studentIndex";
    }

    @RequestMapping(value = "/studentStart")
    public String toStudentStart() {
        return "studentStart";
    }

    @RequestMapping(value = "/studentTable")
    public String toStudentTable() {
        return "studentTable";
    }

    @RequestMapping(value = "/indexPage")
    public String toIndexPage() {
        return "indexPage";
    }

    @RequestMapping(value = "/examPage")
    public String toExamPage() {
        return "examPage";
    }

    @RequestMapping(value = "/addModel")
    public String toAddQuestion() {
        return "addModel";
    }

    @RequestMapping(value = "/modelList")
    public String toModelList() {
        return "modelList";
    }

    @RequestMapping(value = "/teacherIndex")
    public String toTeacherIndex() {
        return "teacher_index";
    }
}
