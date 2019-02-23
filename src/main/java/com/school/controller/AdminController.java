package com.school.controller;

import com.school.entity.Admin;
import com.school.entity.User;
import com.school.service.AdminService;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
    @GetMapping(value = "/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @RequestMapping(value = "/adminLogin")
    public String adminLogin(@RequestParam("adminUserName") String adminUserName,
                             @RequestParam("adminPassword") String adminPassword,
                             HttpSession session,
                             HttpServletRequest request){
        Admin admin = adminService.login(adminUserName, adminPassword);
        if (admin != null){
            session.setAttribute("admin", admin);
            List<User> list = userService.getAllUser();
            request.setAttribute("list", list);
            return "forward:/page/adminTable";
        }
        return "redirect:/page/adminLogin";
    }
}
