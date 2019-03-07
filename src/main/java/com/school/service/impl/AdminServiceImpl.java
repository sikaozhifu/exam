package com.school.service.impl;

import com.school.dao.AdminMapper;
import com.school.entity.Admin;
import com.school.service.AdminService;
import com.school.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String adminUserName, String adminPassword) {
        String password = "";
        try {
             password = MD5Util.toMD5(new String(adminPassword.getBytes("UTF-8")));
        }catch (Exception e){

        }
        return adminMapper.login(adminUserName, password);
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }
}
