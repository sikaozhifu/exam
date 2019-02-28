package com.school.service;

import com.school.entity.Admin;

public interface AdminService {

    Admin login(String adminUserName, String adminPassword);
}
