package com.school.controller;

import com.school.entity.Grade;
import com.school.entity.Role;
import com.school.service.ExamService;
import com.school.service.GradeService;
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
@RequestMapping(value = "/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private ExamService examService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(
            @RequestParam("username")String username,
            @RequestParam("examId")Integer examId
            ){
        Map<String,Object> map = new HashMap<>();
        //判断考生是否在考试行列中
        Grade grade = gradeService.getGradeByExamId(username, examId);
        if (grade != null){
            //考生已考试
            map.put("loginExam", "您已参加考试，无法进行第二次考试！");
            return map;
        }
        return null;
    }

    @RequestMapping(value = "/getGrade",method = RequestMethod.GET)
    public String getGrade(HttpSession session, HttpServletRequest request){
        Role role = (Role) session.getAttribute("role");
        String username = role.getUsername();
        List<Grade> gradeList = gradeService.getGradeByUserName(username);
        request.setAttribute("list", gradeList);
        return "forward:/page/studentTable";
    }
}
