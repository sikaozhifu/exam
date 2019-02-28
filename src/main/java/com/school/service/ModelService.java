package com.school.service;

import com.school.entity.Model;

import java.util.List;

public interface ModelService {

    Integer insert(Model model);

    //查询所有
    List<Model> selectAll();

    //根据type查询
    List<Model> selectByType(String type);
}
