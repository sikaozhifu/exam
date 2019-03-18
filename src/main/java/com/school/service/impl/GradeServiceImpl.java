package com.school.service.impl;

import com.school.dao.GradeMapper;
import com.school.entity.Grade;
import com.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public Integer insertGrade(Grade grade) {
        return gradeMapper.insert(grade);
    }

    @Override
    public List<Grade> getGradeByUserName(String username) {
        return gradeMapper.getGradeByUserName(username);
    }

    @Override
    public Grade getGradeById(Integer gradeId) {
        return gradeMapper.selectByPrimaryKey(gradeId);
    }

    @Override
    public Grade getGradeByExamId(String username,Integer examId) {
        return gradeMapper.getGradeByExamId(username, examId);
    }

    @Override
    public List<Grade> getAllGrade() {
        return gradeMapper.getAllGrade();
    }

    @Override
    public Integer updateGradeById(Grade grade) {
        return gradeMapper.updateByPrimaryKey(grade);
    }

    @Override
    public List<Grade> getGradeByTitle(String title) {
        if (title == null || title.equals("")){
            return gradeMapper.getAllGrade();
        }
        return gradeMapper.getGradeByTitle(title);
    }

    @Override
    public List<Grade> getGradeByUserNameAndTitle(String username, String title) {
        if (title == null || title.equals("")){
            return gradeMapper.getGradeByUserName(username);
        }
        return gradeMapper.getGradeByUserNameAndTitle(username, title);
    }
}
