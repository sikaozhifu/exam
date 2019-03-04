package com.school.service.impl;

import com.school.dao.ExamMapper;
import com.school.entity.Exam;
import com.school.entity.ModelVo;
import com.school.service.ExamServcie;
import com.school.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamServiceImpl implements ExamServcie {

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
}
