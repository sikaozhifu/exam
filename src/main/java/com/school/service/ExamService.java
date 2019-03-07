package com.school.service;

import com.school.entity.Exam;
import com.school.entity.ModelVo;

import java.util.List;

public interface ExamService {

    //新建试卷
    Integer insertExam(Exam exam);

    //整理model
    List<ModelVo>manageModel(String ids);

    //获取所有exam
    List<Exam> getAllExam();

    //获取examById
    Exam getExamById(Integer exam_id);

    Integer deleteExam(Integer exam_id);
    //模糊查询
    List<Exam> getExamByCondition(String exam_name);

    //查询最近七天记录
    List<Exam> getExamRecently();

    //查询所有记录
    Long getExamCount();
}
