package com.school.controller;

import com.school.entity.Exam;
import com.school.entity.ModelVo;
import com.school.entity.Role;
import com.school.service.ExamService;
import com.school.service.ModelService;
import com.school.utils.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private ModelService modelService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addExam(
            @RequestParam("ids")String ids,
            @RequestParam("exam_name")String exam_name,
            @RequestParam("need_time")String need_time,
            @RequestParam("exam_author")String exam_author
            ){
        Map<String,Object> map = new HashMap<>();
        List<Exam> examExist = examService.getExamByCondition(exam_name);
        if (examExist.size()>0){
            map.put("addExam", "试卷名称已存在！");
            return map;
        }
        List<ModelVo> modelVoList = examService.manageModel(ids);
        Exam exam = new Exam();
        exam.setExamName(exam_name);//试卷名称
        exam.setExamType(0);//默认组卷类型
        exam.setNeedTime(need_time);//所需时间
        exam.setExamAuthor(exam_author);//发布者
        String model_ids = "";//试题ids
        String exam_content = "";//试卷内容
        String exam_answer = "";//试卷答案
        String exam_analysis = "";//试卷解析
        float exam_grade = 0;//试卷总分
        for (ModelVo modelVo:modelVoList){
            model_ids += modelVo.getModel().getModelId() + ",";
            exam_content += modelVo.getModel().getContent()+modelVo.getModel().getModelOption();
            exam_answer += modelVo.getModel().getAnswer() + "|";
            exam_analysis += modelVo.getModel().getAnalysis();
            exam_grade = exam_grade + modelVo.getModel().getGrade();
        }
        exam.setModelIds(model_ids);
        exam.setExamContent(exam_content);
        exam.setExamAnswer(exam_answer);
        exam.setExamAnalysis(exam_analysis);
        exam.setExamGrade(exam_grade);
        exam.setExamFlag(0);//默认考试未开启
        Integer result = examService.insertExam(exam);
        if (result == 1){
            map.put("addExam", "试卷添加成功！");
            return map;
//            return "redirect:/exam/select";
        }
        map.put("addExam", "试卷添加失败！请联系管理员...");
//        return "redirect:/page/exam/add";
        return map;
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public String getExamList(@RequestParam("exam_name")String exam_name, HttpServletRequest request){
        if (exam_name == null || exam_name.equals("")){
            List<Exam> examList = examService.getAllExam();
            request.setAttribute("list", examList);
        }else {
            List<Exam> examList = examService.getExamByCondition(exam_name);
            request.setAttribute("list", examList);
        }
        return "forward:/page/exam_list";
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public String getAllExam(HttpServletRequest request, HttpSession session){
        List<Exam> examList = examService.getAllExam();
        request.setAttribute("list", examList);
        Role role = (Role) session.getAttribute("role");
        if (role.getType() == RoleUtil.RoleType.STUDENT){
            return "forward:/page/studentStart";
        }
        return "forward:/page/exam_list";
    }

    @RequestMapping(value = "/getModelVoList",method = RequestMethod.POST)
    @ResponseBody
    public List<ModelVo> getModelVoList(@RequestParam("examId")Integer examId){
        Exam exam = examService.getExamById(examId);
        if (exam == null){
            return null;
        }
        String[] ids = exam.getModelIds().split(",");
        List<ModelVo> modelVoList = new ArrayList<>();
        for (String s:ids){
            ModelVo modelVo = modelService.getModelVo(Integer.parseInt(s));
            modelVoList.add(modelVo);
        }
        return modelVoList;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteExam(@RequestParam("examId")Integer examId){
        Map<String,Object> map = new HashMap<>();
        if (examId == null||examId.equals("")){
            map.put("deleteExam", "删除失败！请联系管理员...");
            return map;
        }
        Integer result = examService.deleteExam(examId);
        if (result == 1){
            map.put("deleteExam", "试卷删除成功！");
        }else {
            map.put("deleteExam", "删除失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateExam(@RequestParam("examId")Integer examId){
        Map<String,Object> map = new HashMap<>();
        if (examId == null||examId.equals("")){
            map.put("deleteExam", "操作失败！请联系管理员...");
            return map;
        }
        Exam exam = examService.getExamById(examId);
        if (exam.getExamFlag() == 0){
            exam.setExamFlag(1);
        }else {
            exam.setExamFlag(0);
        }
        Integer result = examService.updateExam(exam);
        if (result == 1){
            map.put("deleteExam", "试卷操作成功！");
        }else {
            map.put("deleteExam", "操作失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/getExam",method = RequestMethod.GET)
    public String getExam(@RequestParam("exam_id")Integer exam_id, HttpServletRequest request){

        List<ModelVo> list = new ArrayList<>();
        Exam exam = examService.getExamById(exam_id);
        String[] modelIds = exam.getModelIds().split(",");
        for (String s:modelIds){
            ModelVo modelVo = modelService.getModelVo(Integer.parseInt(s));
            list.add(modelVo);
        }
        request.setAttribute("exam", exam);
        request.setAttribute("list", list);
        return "forward:/page/examPage";
    }
}
