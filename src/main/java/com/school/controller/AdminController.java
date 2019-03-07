package com.school.controller;

import com.github.pagehelper.PageInfo;
import com.school.entity.Admin;
import com.school.entity.Exam;
import com.school.entity.User;
import com.school.service.AdminService;
import com.school.service.ExamService;
import com.school.service.ModelService;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String adminLogin(@RequestParam("adminUserName") String adminUserName,
                             @RequestParam("adminPassword") String adminPassword,
                             HttpSession session) {
        Admin admin = adminService.login(adminUserName, adminPassword);
        if (admin != null) {
            Map<String,Object> map = new HashMap<>();
            List<Exam> list = examService.getExamRecently();
            Long examCount = examService.getExamCount();
            Long modelCount = modelService.getModelCount();
            map.put("examCount",examCount);
            map.put("modelCount", modelCount);
            session.setAttribute("map", map);
            session.setAttribute("list", list);
            session.setAttribute("admin", admin);
            return "redirect:/page/adminIndex";
        }
        return "redirect:/page/adminLogin";
    }
}
