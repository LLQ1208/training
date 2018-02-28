package com.acsm.training.controller;/**
 * Created by lq on 2018/2/28.
 */

import com.acsm.training.dao.TeacherEvalDao;
import com.acsm.training.manage.AreaManage;
import com.acsm.training.model.Course;
import com.acsm.training.model.ClassEvaluateModel;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.model.page.EvalLevel;
import com.acsm.training.service.ClassEvaluateService;
import com.acsm.training.service.CourseService;
import com.acsm.training.service.TecentAreaInfoService;
import com.acsm.training.service.WetChatService;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Controller("weChatController")
@RequestMapping("weChat")
public class WeChatController {

    @Autowired
    WetChatService wetChatService;
    @Autowired
    CourseService courseService;


    @Autowired
    private TecentAreaInfoService tecentAreaInfoService;
    @Autowired
    private ClassEvaluateService classEvaluateService;
    /**
     * 进入手机课程页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getClassDetail",method= RequestMethod.GET)
    @ResponseBody
    public String getClassDetail(HttpServletRequest request,HttpServletResponse response){
        Integer classScheduleId = Integer.valueOf(request.getParameter("classScheduleId"));
        CourseSchedule courseSchedule = courseService.queryCourseScheduleById(classScheduleId);
        List<CourseModel> courseModelList = wetChatService.queryCourseDetail(classScheduleId);
        JSONObject json = new JSONObject();
        json.put("id",classScheduleId);
        json.put("className",courseSchedule.getClassName());
        json.put("list",courseModelList);
        return json.toString();
    }

    @RequestMapping(value="/getClassCity",method= RequestMethod.GET)
    @ResponseBody
    public ClassEvaluateModel getClassCity(HttpServletRequest request, HttpServletResponse response){
        List<TecentAreaInfo> areaInfoList = tecentAreaInfoService.queryProvinceList();
        Integer classScheduleId = Integer.valueOf(request.getParameter("classScheduleId"));
        CourseSchedule courseSchedule = courseService.queryCourseScheduleById(classScheduleId);
        ClassEvaluateModel classEvaluateModel = new ClassEvaluateModel();
        classEvaluateModel.setTitle(courseSchedule.getClassName());
        classEvaluateModel.setList(areaInfoList);
        return classEvaluateModel;
    }

    @RequestMapping(value="/getClassCheckOut",method= RequestMethod.POST)
    @ResponseBody
    public String getClassCheckOut(HttpServletRequest request, HttpServletResponse response){
        Integer classScheduleId = Integer.valueOf(request.getParameter("classScheduleId"));
        String studentName = request.getParameter("studentName");
        String studentPhone = request.getParameter("studentPhone");
        List<Object[]> object = classEvaluateService.queryByNameByPhoneById(classScheduleId,studentName,studentPhone);
        Integer sumCalc = Integer.valueOf(object.get(0)[0].toString());
        if((null != sumCalc) && (sumCalc != 0)){
            return "fail";
        }
        return "succeed";
    }

    @RequestMapping(value="/classSave",method= RequestMethod.POST)
    @ResponseBody
    public String classSave(HttpServletRequest request,HttpServletResponse response){
        String name = request.getParameter("studentName");
        String phone = request.getParameter("studentPhone");
        Integer classScheduleId = Integer.valueOf(request.getParameter("classScheduleId"));
        Integer areaId = Integer.valueOf(request.getParameter("areaId"));
        Integer satisfaction = Integer.valueOf(request.getParameter("satisfaction"));
        Integer accommodations = Integer.valueOf(request.getParameter("accommodations"));
        Integer considerate = Integer.valueOf(request.getParameter("considerate"));
        Integer rationality = Integer.valueOf(request.getParameter("rationality"));
        Integer serviceAttitude = Integer.valueOf(request.getParameter("serviceAttitude"));
        Integer gain = Integer.valueOf(request.getParameter("gain"));
        classEvaluateService.addClassEvaluate(classScheduleId,name,phone,areaId,satisfaction,accommodations,considerate,rationality,serviceAttitude,gain);
        return "succeed";
    }

    /**
     * 进去老师评价页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/teachEval",method= RequestMethod.GET)
    @ResponseBody
    public String teachEval(HttpServletRequest request,HttpServletResponse response){
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Course course = courseService.queryCourseById(courseId);
        List<TecentAreaInfo> provinceList = new ArrayList<>();
        provinceList.addAll(AreaManage.getProviceList());
        JSONObject json = new JSONObject();
        json.put("provinceList",provinceList);
        json.put("courseId",courseId);
        json.put("course",course);
        json.put("evalLevelList", getEvalLevelList());
        return json.toString();
    }

    /**
     * 老师评价提交
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/teachEvalSave",method= RequestMethod.POST)
    @ResponseBody
    public String teachEvalSave(HttpServletRequest request,HttpServletResponse response){
        Integer courseId = ServletRequestUtils.getIntParameter(request, "courseId", 0);
        String studentName = ServletRequestUtils.getStringParameter(request, "studentName", null);
        String phone = ServletRequestUtils.getStringParameter(request, "phone", null);
        Integer provinceAreaId = ServletRequestUtils.getIntParameter(request, "provinceAreaId", 0);
        Integer evalId = ServletRequestUtils.getIntParameter(request, "evalId", 0);
        Integer starEvalOne = ServletRequestUtils.getIntParameter(request, "starEvalOne", 0);
        Integer starEvalTwo = ServletRequestUtils.getIntParameter(request, "starEvalTwo", 0);
        Integer starEvalThree = ServletRequestUtils.getIntParameter(request, "starEvalThree", 0);
        JSONObject json = wetChatService.saveTeacherEval(courseId,studentName,phone,provinceAreaId,evalId,
                starEvalOne,starEvalTwo,starEvalThree);
        return json.toString();
    }

    private List<EvalLevel> getEvalLevelList(){
        List<EvalLevel> evalLevelList = new ArrayList<EvalLevel>(){{
            add(new EvalLevel(1,"很好"));
            add(new EvalLevel(2,"一版"));
            add(new EvalLevel(3,"不好"));
        }};
        return evalLevelList;
    }

}