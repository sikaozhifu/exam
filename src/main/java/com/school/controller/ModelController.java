package com.school.controller;

import com.school.entity.Model;
import com.school.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Integer addModel(Model model, HttpSession session){
//        User user = (User) session.getAttribute("user");
//        if (user == null){
//            return null;
//        }
//        model.setAuthor(user.getName());
        model.setAuthor("tom");
        Integer result = modelService.insert(model);
        if (result == 1){
            return result;
        }
        return null;
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public String selectModel(@RequestParam("type") String type, HttpServletRequest request){
        if (type == null){
            List<Model> list = modelService.selectAll();
            request.setAttribute("list", list);
        }else {
            List<Model> list = modelService.selectByType(type);
            request.setAttribute("list", list);
        }
        return "forward:/page/modelList";
    }
}
