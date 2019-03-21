package com.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.dao.ExamMapper;
import com.school.entity.Exam;
import com.school.entity.ModelVo;
import com.school.service.ExamService;
import com.school.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ModelService modelService;
    @Override
    public Integer insertExam(Exam exam) {
        return examMapper.insert(exam);
    }

    @Override
    public List<ModelVo> manageModel(String ids) {
        String[] split = ids.split(",");
        List<ModelVo> modelList = new ArrayList<>();
        Comparator<ModelVo> comparator = (m1, m2) -> {
           if (m1.getModel().getType()!=m2.getModel().getType()){
               return m1.getModel().getType()-m2.getModel().getType();
           }else {
               if (!m1.getModel().getTitle().equals(m2.getModel().getTitle())){
                   return m1.getModel().getTitle().compareTo(m2.getModel().getTitle());
               }else {
                   return m1.getModel().getModelId()-m2.getModel().getModelId();
               }
           }
        };
        for (String s:split){
            int model_id = Integer.parseInt(s);
            ModelVo m = modelService.getModelVo(model_id);
            modelList.add(m);
        }
        Collections.sort(modelList,comparator);
        return modelList;
    }

    @Override
    public List<ModelVo> manageModel(Set<ModelVo> modelVoSet) {
        Comparator<ModelVo> comparator = (m1, m2) -> {
            if (m1.getModel().getType()!=m2.getModel().getType()){
                return m1.getModel().getType()-m2.getModel().getType();
            }else {
                if (!m1.getModel().getTitle().equals(m2.getModel().getTitle())){
                    return m1.getModel().getTitle().compareTo(m2.getModel().getTitle());
                }else {
                    return m1.getModel().getModelId()-m2.getModel().getModelId();
                }
            }
        };
        List<ModelVo> modelList = new ArrayList<>(modelVoSet);

        Collections.sort(modelList,comparator);
        return modelList;
    }

    @Override
    public List<Exam> getAllExam() {
        return examMapper.getAllExam();
    }


    @Override
    public Exam getExamById(Integer exam_id) {
        return examMapper.selectByPrimaryKey(exam_id);
    }

    @Override
    public Integer deleteExam(Integer exam_id) {
        return examMapper.deleteByPrimaryKey(exam_id);
    }

    @Override
    public List<Exam> getExamByCondition(String exam_name) {
        return examMapper.getExamByCondition(exam_name);
    }

    @Override
    public List<Exam> getExamRecently() {
        List<Exam> examList = examMapper.getExamRecently();
        if (examList.size() >= 3){
            //取前三条数据
            return examList.subList(0, 3);
        }
        return examList;
    }

    @Override
    public Long getExamCount() {
        return examMapper.getExamCount();
    }

    @Override
    public Integer updateExam(Exam exam) {
        return examMapper.updateByPrimaryKey(exam);
    }

    @Override
    public PageInfo<Exam> getAllExamByCondition(Integer currentPage, Integer pageSize, String condition) {

        PageHelper.startPage(currentPage, pageSize);
        List<Exam> list;
        if (condition == null || condition.equals("")){//都为空
            list = examMapper.getAllExam();
            PageInfo<Exam> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }else {
            list = examMapper.getExamByCondition(condition);
            PageInfo<Exam> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
    }
}
