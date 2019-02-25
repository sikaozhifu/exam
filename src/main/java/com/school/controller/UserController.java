package com.school.controller;

import com.school.entity.User;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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
                        @RequestParam("role") Integer role){
        User user = userService.login(username, password);
        if (user == null){
            return "redirect:/page/login";
        }
        if (role.equals(0)){
            //学生
            return "redirect:/page/studentIndex";
        }else if (role.equals(1)){
            //老师
            return "redirect:/page/teacherIndex";
        }
        return "redirect:/page/login";
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
