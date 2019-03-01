package com.school.controller;

import com.school.entity.Model;
import com.school.entity.ModelVo;
import com.school.entity.User;
import com.school.service.ModelService;
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
    public String addModel(Model model, HttpSession session){
        //TODO添加作者
        User user = (User) session.getAttribute("user");
        if (user == null){
            return "redirect:/page/login";
        }
        model.setAuthor(user.getName());
//        model.setAuthor("tom");
        if (model.getTitle().equals("")||model.getTitle() == null){
            //添加标题
            String title = TitleUtil.getTitle(model.getContent());
            model.setTitle(title);
        }
        Integer result = modelService.insert(model);
        if (result == 1){
            return "redirect:/model/select?type";
        }
        return "redirect:/page/login";
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public String selectModel(@RequestParam("type") Integer type, HttpServletRequest request){
        if (type == null){
            List<ModelVo> list = modelService.selectAll();
            request.setAttribute("list", list);
        }else {
            List<ModelVo> list = modelService.selectByType(type);
            request.setAttribute("list", list);
        }
        return "forward:/page/modelList";
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
