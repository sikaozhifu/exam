package com.school.controller;

import com.school.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page")
public class PageController {

    public String isAdmin(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){
            return "redirect:/page/adminLogin";
        }
        return "success";
    }
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
    public String toAdminTable(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "adminTable";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/adminIndex")
    public String toAdminIndex(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "adminIndex";
        }else {
            return result;
        }
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
    public String toAddModel() {
        return "addModel";
    }

    @RequestMapping(value = "/modelList")
    public String toModelList(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "modelList";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/teacherIndex")
    public String toTeacherIndex() {
        return "teacher_index";
    }

    @RequestMapping(value = "/addExam")
    public String toAddExam(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "add_exam";
        }else {
            return result;
        }
    }
    @RequestMapping(value = "/exam_list")
    public String toExamList(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "exam_list";
        }else {
            return result;
        }
    }
}
