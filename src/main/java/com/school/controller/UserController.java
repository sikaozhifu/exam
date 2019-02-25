package com.school.controller;

import com.school.entity.User;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("role") Integer role,
                        HttpServletRequest request,
                        HttpSession session){
        User user = userService.login(username, password);
        if (user == null){
            request.setAttribute("loginMessage", "用户名或密码错误");
            return "forward:/page/login";
        }
        if (role.equals(0)){
            //学生
            session.setAttribute("login", user);
            return "redirect:/page/studentIndex";
        }else if (role.equals(1)){
            //可能为教师
            //查找数据库中该用户是否为教师
            if (user.getRole().equals(1)){
                session.setAttribute("login", user);
                return "redirect:/page/teacherIndex";
            }else {
                request.setAttribute("loginMessage", "您不是教师");
            }
        }
        return "forward:/page/login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") Integer role,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email){
        User u = userService.getUserByUserName(username);
        if (u != null){
            System.out.println("学号已注册！");
            return "redirect:/page/register";
        }
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        Integer result = userService.register(user);
        if (result == 1){
            return "redirect:/page/login";
        }
        return "redirect:/page/register";
    }
}
