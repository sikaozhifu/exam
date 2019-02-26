package com.school.controller;

import com.school.entity.Admin;
import com.school.entity.User;
import com.school.service.AdminService;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String adminLogin(@RequestParam("adminUserName") String adminUserName,
                             @RequestParam("adminPassword") String adminPassword,
                             HttpSession session) {
        Admin admin = adminService.login(adminUserName, adminPassword);
        if (admin != null) {
            session.setAttribute("admin", admin);
            return "redirect:/page/adminIndex";
        }
        return "redirect:/page/adminLogin";
    }

    @RequestMapping(value = "/getAllUser")
    public String adminGetAllUser(HttpSession session, HttpServletRequest request) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null){
            List<User> list = userService.getAllUser();
            request.setAttribute("list", list);
            return "forward:/page/adminTable";
        }
        return "redirect:/page/adminLogin";
    }
}
