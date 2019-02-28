package com.school.service.impl;

import com.school.dao.ModelMapper;
import com.school.entity.Model;
import com.school.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Integer insert(Model model) {
        return modelMapper.insert(model);
    }

    @Override
    public List<Model> selectAll() {
        return modelMapper.selectAll();
    }

    @Override
    public List<Model> selectByType(String type) {
        return modelMapper.selectByType(type);
    }
}
