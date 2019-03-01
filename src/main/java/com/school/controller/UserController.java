package com.school.controller;

import com.school.entity.User;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    @ResponseBody
    public User getUser(@RequestParam("id") Integer id){
        if (id != null){
            return userService.getUserById(id);
        }
        return null;
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
            //可能为学生
            //查询数据库中该用户是否为学生
            if (user.getRole().equals(0)){
                session.setAttribute("user", user);
                return "redirect:/page/studentIndex";
            }else {
                request.setAttribute("loginMessage", "您不是学生");
            }
        }else if (role.equals(1)){
            //可能为教师
            //查找数据库中该用户是否为教师
            if (user.getRole().equals(1)){
                session.setAttribute("user", user);
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

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteUser(@RequestParam("id") Integer id){
        if (id == null){
            return null;
        }
//        return userService.deleteUserById(deleteId);
        return 1;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUser(User user){
        Map<String,Object> map = new HashMap<>();
        //检查是否修改了学号
        User u = userService.getUserById(user.getId());
        if (user.getUsername().equals(u.getUsername())){
            //没有修改学号
            Integer result = userService.updateUser(user);
            if (result == 1){
                map.put("update_user", "修改成功！");
                return map;
            }
        }else {
            //修改了学号
            User userOther = userService.getUserByUserName(user.getUsername());
            if (userOther != null){
                //学号已经注册
                map.put("update_user", "学号已经注册！");
                return map;
            }else {
                //修改了学号并且学号没有被注册
                Integer result = userService.updateUser(user);
                if (result == 1){
                    map.put("update_user", "修改成功！");
                    return map;
                }
            }
        }
        map.put("update_user", "修改失败！请联系管理员...");
        return map;
    }
}
