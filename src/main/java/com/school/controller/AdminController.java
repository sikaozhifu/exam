package com.school.controller;

import com.school.entity.Admin;
import com.school.entity.Exam;
import com.school.service.AdminService;
import com.school.service.ExamService;
import com.school.service.ModelService;
import com.school.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String adminLogout(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null){
            //账号注销
            session.removeAttribute("admin");
        }
        return "redirect:/page/adminLogin";
    }

    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> adminUpdatePassword(
            @RequestParam("old_password")String old_password,
            @RequestParam("new_password")String new_password,
            HttpSession session
            ){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){
           return null;
        }
        Map<String,Object> map = new HashMap<>();
        String password = admin.getAdminPassword();
        try {
            System.out.println(MD5Util.toMD5(new String(old_password.getBytes("UTF-8"))));
        }catch (Exception e){

        }
        if (!password.equals(MD5Util.toMD5(old_password))){
            map.put("updatePassword", "原密码错误！");
            return map;
        }
        admin.setAdminPassword(MD5Util.toMD5(new_password));
        Integer result = adminService.updateAdmin(admin);
        if (result == 1){
            map.put("updatePassword", "修改密码成功！");
        }else {
            map.put("updatePassword", "修改密码失败！请联系管理员...");
        }
        return map;
    }
}
