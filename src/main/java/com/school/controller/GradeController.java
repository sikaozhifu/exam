package com.school.controller;

import com.school.service.ExamService;
import com.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private ExamService examService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestParam("username")String username){
        Map<String,Object> map = new HashMap<>();
        //判断考生是否在考试行列中
        if (true){
            //在考生当中
            map.put("studentLogin", "验证成功");
        }
        return map;
    }
}
