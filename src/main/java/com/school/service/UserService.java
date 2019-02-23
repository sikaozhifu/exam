package com.school.service;

import com.school.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    //登录
    User login(String username,String password);

    List<User> getAllUser();

    Integer register(User user);

    User getUserByUserName(String username);
}
