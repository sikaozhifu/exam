package com.school.controller;

import com.school.entity.Model;
import com.school.entity.ModelVo;
import com.school.entity.Role;
import com.school.service.ModelService;
import com.school.utils.HtmlUtil;
import com.school.utils.TitleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addModel(
            @RequestParam("title")String title,
            @RequestParam("content")String content,
            @RequestParam("modelOption")String modelOption,
            @RequestParam("answer")String answer,
            @RequestParam("type")Integer type,
            @RequestParam("analysis")String analysis,
            @RequestParam("grade")Float grade,
            @RequestParam("difficulty")Integer difficulty,
            HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Role role = (Role) session.getAttribute("role");
        if (title.equals("")||title == null){
            //添加标题
            title = TitleUtil.getTitle(content);
        }

        Model model = new Model();
        model.setAuthor(role.getName());
        model.setTitle(title);
        model.setContent(content);
        model.setModelOption(modelOption);
        model.setAnswer(HtmlUtil.delHTMLTag(answer));
        model.setType(type);
        model.setAnalysis(analysis);
        model.setGrade(grade);
        model.setDifficulty(difficulty);
        Integer result = modelService.insert(model);
        if (result == 1){
            map.put("addModel", "添加成功！");
            return map;
        }
        map.put("addModel", "添加失败！请联系管理员...");
        return map;
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public String selectModel(@RequestParam("type") Integer type, HttpServletRequest request){
        if (type == null||type.equals("")||type == 0){
            List<ModelVo> list = modelService.selectAll();
            request.setAttribute("list", list);
        }else {
            List<ModelVo> list = modelService.selectByType(type);
            request.setAttribute("list", list);
        }
        return "forward:/page/modelList";
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public String getModelList(@RequestParam("title") String title, HttpServletRequest request){
        if (title == null || title.equals("")){
            List<ModelVo> list = modelService.selectAll();
            request.setAttribute("list", list);
        }else {
            List<ModelVo> list = modelService.selectByTitle(title);
            request.setAttribute("list", list);
        }
        return "forward:/page/modelList";
    }

    @RequestMapping(value = "/getALLModel",method = RequestMethod.POST)
    @ResponseBody
    public List<ModelVo> getALLModel(@RequestParam("type") Integer type){
        if (type == null||type.equals("")||type == 0){
            return modelService.selectAll();
        }
        return modelService.selectByType(type);
    }

    @RequestMapping(value = "/getModel",method = RequestMethod.POST)
    @ResponseBody
    public ModelVo getModel(@RequestParam("modelId") Integer modelId){
        if (modelId == null){
            return null;
        }
        return modelService.getModelVo(modelId);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateModel(Model model){
        Map<String,Object> map = new HashMap<>();
        Integer result = modelService.updateModel(model);
        if (result == 1){
            map.put("updateModel", "试题修改成功！");
        }else {
            map.put("updateModel", "修改失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteModel(@RequestParam("modelId") Integer modelId){
        Map<String,Object> map = new HashMap<>();
        if (modelId == null||modelId.equals("")){
            map.put("deleteModel", "删除失败！请联系管理员...");
            return map;
        }
        Integer result = modelService.deleteModel(modelId);
        if (result == 1){
            map.put("deleteModel", "试题删除成功！");
        }else {
            map.put("deleteModel", "删除失败！请联系管理员...");
        }
        return map;
    }
}
