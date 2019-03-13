package com.school.service.impl;

import com.school.dao.GradeMapper;
import com.school.entity.Grade;
import com.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public Integer insertGrade(Grade grade) {
        return gradeMapper.insert(grade);
    }
}
