package com.school.controller;

import com.school.entity.*;
import com.school.service.ExamService;
import com.school.service.GradeService;
import com.school.service.ModelService;
import com.school.service.RecordService;
import com.school.utils.HtmlUtil;
import com.school.utils.ModelTypeUtil;
import com.school.utils.RoleUtil;
import com.school.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping(value = "/exam")
public class ExamController {

    //试题map
    public Map<Integer, ModelVo> modelMap = new ConcurrentHashMap<>();

    //答案map
    public Map<Integer, String> answerMap = new ConcurrentHashMap<>();

    //当前试题序号
    private static Integer num;

    private static Long startTime;//考试开始时间

    private static Long endTime;//考试结束时间

    @Autowired
    private ExamService examService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addExam(
            @RequestParam("ids") String ids,
            @RequestParam("exam_name") String exam_name,
            @RequestParam("need_time") String need_time,
            @RequestParam("exam_author") String exam_author
    ) {
        Map<String, Object> map = new HashMap<>();
        List<Exam> examExist = examService.getExamByCondition(exam_name);
        if (examExist.size() > 0) {
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
        for (ModelVo modelVo : modelVoList) {
            model_ids += modelVo.getModel().getModelId() + ",";
            exam_content += modelVo.getModel().getContent() + modelVo.getModel().getModelOption();
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
        if (result == 1) {
            map.put("addExam", "试卷添加成功！");
            return map;
//            return "redirect:/exam/select";
        }
        map.put("addExam", "试卷添加失败！请联系管理员...");
//        return "redirect:/page/exam/add";
        return map;
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public String getExamList(@RequestParam("exam_name") String exam_name, HttpServletRequest request) {
        if (exam_name == null || exam_name.equals("")) {
            List<Exam> examList = examService.getAllExam();
            request.setAttribute("list", examList);
        } else {
            List<Exam> examList = examService.getExamByCondition(exam_name);
            request.setAttribute("list", examList);
        }
        return "forward:/page/exam_list";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String getAllExam(HttpServletRequest request, HttpSession session) {
        List<Exam> examList = examService.getAllExam();
        request.setAttribute("list", examList);
        Role role = (Role) session.getAttribute("role");
        if (role.getType() == RoleUtil.RoleType.STUDENT) {
            return "forward:/page/studentStart";
        }
        return "forward:/page/exam_list";
    }

    @RequestMapping(value = "/getModelVoList", method = RequestMethod.POST)
    @ResponseBody
    public List<ModelVo> getModelVoList(@RequestParam("examId") Integer examId) {
        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return null;
        }
        String[] ids = exam.getModelIds().split(",");
        List<ModelVo> modelVoList = new ArrayList<>();
        for (String s : ids) {
            ModelVo modelVo = modelService.getModelVo(Integer.parseInt(s));
            modelVoList.add(modelVo);
        }
        return modelVoList;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteExam(@RequestParam("examId") Integer examId) {
        Map<String, Object> map = new HashMap<>();
        if (examId == null || examId.equals("")) {
            map.put("deleteExam", "删除失败！请联系管理员...");
            return map;
        }
        Integer result = examService.deleteExam(examId);
        if (result == 1) {
            map.put("deleteExam", "试卷删除成功！");
        } else {
            map.put("deleteExam", "删除失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateExam(@RequestParam("examId") Integer examId) {
        Map<String, Object> map = new HashMap<>();
        if (examId == null || examId.equals("")) {
            map.put("deleteExam", "操作失败！请联系管理员...");
            return map;
        }
        Exam exam = examService.getExamById(examId);
        if (exam.getExamFlag() == 0) {
            exam.setExamFlag(1);
        } else {
            exam.setExamFlag(0);
        }
        Integer result = examService.updateExam(exam);
        if (result == 1) {
            map.put("deleteExam", "试卷操作成功！");
        } else {
            map.put("deleteExam", "操作失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/getExam", method = RequestMethod.GET)
    public String getExam(@RequestParam("exam_id") Integer exam_id, HttpSession session) {

        List<ModelVo> list = new ArrayList<>();
        Exam exam = examService.getExamById(exam_id);
        String[] modelIds = exam.getModelIds().split(",");
        for (String s : modelIds) {
            ModelVo modelVo = modelService.getModelVo(Integer.parseInt(s));
            if (modelVo != null){
                list.add(modelVo);
            }
        }
        for (int i = 1; i <= list.size(); i++) {
            modelMap.put(i, list.get(i - 1));
        }
        session.setAttribute("time", Float.valueOf(exam.getNeedTime()) * 60);
        session.setAttribute("size", list.size());
        session.setAttribute("exam", exam);
        num = 1;//默认第一题
        //计算当前时间以及考试结束时间
        if (session.getAttribute("startTime") == null) {
            startTime = System.currentTimeMillis();
            session.setAttribute("startTime", startTime);
            endTime = startTime + Long.parseLong(exam.getNeedTime()) * 60 * 1000;
            session.setAttribute("endTime", endTime);
        }
        session.setAttribute("num", num);
        session.setAttribute("modelVo", modelMap.get(1));//第一题
        return "forward:/page/examPage";
    }

    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getModelVo(
            @RequestParam("key") Integer key,
            @RequestParam("answer") String answer,
            HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        answerMap.put(num, answer);
        num = key;
        session.setAttribute("num", num);
        session.setAttribute("modelVo", modelMap.get(key));
        map.put("saveAnswer", "答案保存成功！");
        return map;
    }

    @Transactional
    @RequestMapping(value = "/saveExam", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveExam(
            @RequestParam("num") Integer num,
            @RequestParam("answer") String answer,
            HttpSession session
    ) {
        Map<String, Object> map = new HashMap<>();
        answerMap.put(num, answer);
        //比对答案

        Role role = (Role) session.getAttribute("role");
        Exam exam = (Exam) session.getAttribute("exam");
        String username = role.getUsername();//用户名
        String name = role.getName();//姓名
        String title = exam.getExamName();//试卷名称
        Integer examId = exam.getExamId();//试卷id
        String groupName = "15070842";//班级名称    暂不处理
        Long startExamTime = (Long) session.getAttribute("startTime");//考试开始时间
        String spendTime = TimeUtil.getTimeReduce(startExamTime, System.currentTimeMillis());//考试所用时间
        Float score = 0f;
        for (Map.Entry<Integer, ModelVo> entry : modelMap.entrySet()) {
            Integer key = entry.getKey();
            ModelVo modelVo = entry.getValue();
            //创建考试试题记录，方便以后查看
            Record record = new Record();
            record.setUsername(username);
            record.setName(name);
            record.setExamId(examId);
            record.setModelId(modelVo.getModel().getModelId());
            record.setAnswer(answerMap.get(key));
            recordService.insertRecord(record);

            if (modelVo.getModel().getType() == ModelTypeUtil.Type.TRANSLATE.getValue() || modelVo.getModel().getType() == ModelTypeUtil.Type.WRITING.getValue()) {
                continue;
            }
            String[] correctAnswer = modelVo.getModel().getAnswer().split("\\|");
            Float perGrade = modelVo.getModel().getGrade() / correctAnswer.length;
            //处理userInput

            String userInput = HtmlUtil.delHTMLTag(answerMap.get(key)).trim();
            String[] userAnswer = HtmlUtil.format(userInput).split("(?<!^)(?=[A-Z])");

            //用户提交的answer
            String[] submitAnswer = new String[correctAnswer.length];
            System.arraycopy(userAnswer, 0, submitAnswer, 0, userAnswer.length);
            for (int i = 0; i < correctAnswer.length; i++) {
                if (correctAnswer[i].equals(submitAnswer[i])) {
                    score += perGrade;
                }
            }
        }
        //创建考卷信息grade
        Grade grade = new Grade();
        grade.setUsername(username);
        grade.setName(name);
        grade.setTitle(title);
        grade.setGroupName(groupName);
        grade.setSpendTime(spendTime);
        grade.setScore(score);
        grade.setExamId(examId);
        Integer result = gradeService.insertGrade(grade);
        if (result == 1){
            map.put("saveExam", "试卷提交成功！");
        }
        return map;
    }
}
