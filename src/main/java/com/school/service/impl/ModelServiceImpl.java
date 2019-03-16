package com.school.service.impl;

import com.school.dao.ModelMapper;
import com.school.dao.TypeMapper;
import com.school.entity.Model;
import com.school.entity.ModelVo;
import com.school.entity.Type;
import com.school.service.ModelService;
import com.school.utils.DifficultyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Integer insert(Model model) {
        return modelMapper.insert(model);
    }

    @Override
    public List<ModelVo> selectAll() {
        List<ModelVo> modelVoList = new ArrayList<>();
        List<Model> list = modelMapper.selectAll();
        for (Model model:list){
            modelVoList.add(modelToModelVo(model));
        }
        return modelVoList;
    }

    @Override
    public List<ModelVo> selectByType(Integer type) {
        List<ModelVo> modelVoList = new ArrayList<>();
        List<Model> list = modelMapper.selectByType(type);
        for (Model model:list){
            modelVoList.add(modelToModelVo(model));
        }
        return modelVoList;
    }

    @Override
    public ModelVo getModelVo(Integer modelId) {
        Model model = modelMapper.selectByPrimaryKey(modelId);
        return modelToModelVo(model);
    }

    @Override
    public Integer updateModel(Model model) {
        return modelMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Integer deleteModel(Integer modelId) {
        return modelMapper.deleteByPrimaryKey(modelId);
    }

    @Override
    public List<ModelVo> selectByTitle(String title) {
        List<ModelVo> modelVoList = new ArrayList<>();
        List<Model> list = modelMapper.selectByTitle(title);
        for (Model model:list){
            modelVoList.add(modelToModelVo(model));
        }
        return modelVoList;
    }

    @Override
    public Long getModelCount() {
        return modelMapper.getModelCount();
    }

    @Override
    public Model getModelById(Integer id) {
        return modelMapper.selectByPrimaryKey(id);
    }

    /**
     * //转化为包装类ModelVo
     * @param model
     * @return
     */
    public ModelVo modelToModelVo(Model model){
        ModelVo modelVo = new ModelVo();
        Type type = typeMapper.selectByPrimaryKey(model.getType());
        String difficulty = DifficultyUtil.getDifficulty(model.getDifficulty());
        modelVo.setModel(model);
        modelVo.setType(type.getType());
        modelVo.setDifficulty(difficulty);
        return modelVo;
    }
}
