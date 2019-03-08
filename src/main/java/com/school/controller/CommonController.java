package com.school.controller;

import com.school.entity.Admin;
import com.school.entity.Role;
import com.school.entity.User;
import com.school.service.AdminService;
import com.school.service.UserService;
import com.school.utils.MD5Utils;
import com.school.utils.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/common")
public class CommonController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    //修改信息
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> commonUpdateInfo(
            @RequestParam("name")String name,
            @RequestParam("email")String email,
            HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        if (role.getType().equals(RoleUtil.RoleType.ADMIN)){//管理员
            Admin admin = (Admin) session.getAttribute("admin");
            admin.setAdminName(name);
            admin.setAdminEmail(email);
            Integer result = adminService.updateAdmin(admin);
            if (result == 1){
                map.put("commonUpdateInfo", "信息修改成功！");
                //role是否会变化
                return map;
            }else {
                map.put("commonUpdateInfo", "信息修改失败！请联系管理员...");
                return map;
            }
        }else {//用户
            User user = (User) session.getAttribute("user");
            user.setName(name);
            user.setEmail(email);
            Integer result = userService.updateUser(user);
            if (result == 1){
                map.put("commonUpdateInfo", "信息修改成功！");
                //role是否会变化
                return map;
            }else {
                map.put("commonUpdateInfo", "信息修改失败！请联系管理员...");
                return map;
            }
        }
    }

    //修改密码
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> adminUpdatePassword(
            @RequestParam("old_password")String old_password,
            @RequestParam("new_password")String new_password,
            HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        if (role.getType().equals(RoleUtil.RoleType.ADMIN)){
            Admin admin = (Admin) session.getAttribute("admin");
            String password = admin.getAdminPassword();
            if (!password.equals(MD5Utils.md5(old_password))){
                map.put("commonUpdatePassword", "原密码错误！");
                return map;
            }
            admin.setAdminPassword(MD5Utils.md5(new_password));
            Integer result = adminService.updateAdmin(admin);
            if (result == 1){
                map.put("commonUpdatePassword", "修改密码成功！");
            }else {
                map.put("commonUpdatePassword", "修改密码失败！请联系管理员...");
            }
            return map;
        }else {
            User user = (User) session.getAttribute("user");
            String password = user.getPassword();
            if (!password.equals(MD5Utils.md5(old_password))){
                map.put("commonUpdatePassword", "原密码错误！");
                return map;
            }
            user.setPassword(MD5Utils.md5(new_password));
            Integer result = userService.updateUser(user);
            if (result == 1){
                map.put("commonUpdatePassword", "修改密码成功！");
            }else {
                map.put("commonUpdatePassword", "修改密码失败！请联系管理员...");
            }
            return map;
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String commonLogout(HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role != null){
            //账号注销
            session.removeAttribute("user");
            session.removeAttribute("role");
            session.removeAttribute("admin");
        }
        return "redirect:/page/login";
    }
}
