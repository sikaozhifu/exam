package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/exam")
public class ExamController {

    @RequestMapping(value = "/add")
    public String addExam(){
        return null;
    }
}
