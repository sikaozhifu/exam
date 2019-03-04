package com.school.service;

import com.school.entity.Exam;
import com.school.entity.ModelVo;

import java.util.List;

public interface ExamServcie {

    //新建试卷
    Integer insertExam(Exam exam);

    //整理model
    List<ModelVo>manageModel(String ids);
}
